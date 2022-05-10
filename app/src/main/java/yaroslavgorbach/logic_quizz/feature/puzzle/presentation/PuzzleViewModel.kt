package yaroslavgorbach.logic_quizz.feature.puzzle.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import yaroslavgorbach.logic_quizz.PUZZLE_NAME_ARG
import yaroslavgorbach.logic_quizz.data.common.PuzzleRepo
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.common.model.findNext
import yaroslavgorbach.logic_quizz.data.puzzle.model.Hint
import yaroslavgorbach.logic_quizz.data.puzzle.model.Puzzle
import yaroslavgorbach.logic_quizz.data.puzzle.model.table.Table
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleAction
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleUiMessage
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleViewState
import yaroslavgorbach.logic_quizz.utills.AdManager
import yaroslavgorbach.logic_quizz.utills.UiMessage
import yaroslavgorbach.logic_quizz.utills.UiMessageManager
import yaroslavgorbach.logic_quizz.utills.compare
import javax.inject.Inject

@HiltViewModel
class PuzzleViewModel @Inject constructor(
    private val puzzleRepo: PuzzleRepo,
    private val adManager: AdManager,
    savedState: SavedStateHandle
) : ViewModel() {
    val puzzleName: PuzzleName = savedState[PUZZLE_NAME_ARG]!!

    private val pendingActions = MutableSharedFlow<PuzzleAction>()

    private var puzzle: MutableStateFlow<Puzzle?> = MutableStateFlow(null)

    private var puzzleHints: MutableStateFlow<MutableList<Hint>> = MutableStateFlow(ArrayList())

    private var hintedTitles: MutableStateFlow<Pair<String, String>?> = MutableStateFlow(null)

    private var isCheckAnswerVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val uiMessageManager: UiMessageManager<PuzzleUiMessage> = UiMessageManager()

    val state: StateFlow<PuzzleViewState> = combine(
        isCheckAnswerVisible,
        puzzle,
        hintedTitles,
        puzzleHints,
        uiMessageManager.message,
    ) { isCheckAnswerVisible, puzzle, hintedTitles, hints, message ->
        initHints(puzzle)

        PuzzleViewState(
            isHintByAdAvailable = hints.any { it.isVisible.not() },
            puzzle = puzzle?.copy(tables = puzzle.tables.map { table ->
                table.copy(cells = table.cells.markIncorrectCells(table))
            }),
            message = message,
            hintedTitles = hintedTitles,
            isCheckAnswerVisible = isCheckAnswerVisible,
            puzzleHints = hints.filter { it.isVisible },
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = PuzzleViewState.Test
    )

    private suspend fun initHints(puzzle: Puzzle?) {
        if (puzzleHints.value.isEmpty()) {
            puzzleHints.emit(puzzle?.hints?.toMutableList() ?: ArrayList())
        }
    }

    init {
        adManager.loadRewordAd()

        viewModelScope.launch {
            uiMessageManager.emitMessage(UiMessage(PuzzleUiMessage.ShowStoryDialog))
            loadPuzzle(puzzleName)
        }

        viewModelScope.launch {
            pendingActions.collect { action ->
                when (action) {
                    is PuzzleAction.OnCell -> {
                        handleTitleHint(action)
                        handleCellClick(action)
                    }

                    PuzzleAction.CheckAnswer -> {
                        checkAnswers()
                    }
                    PuzzleAction.TableUpdated -> {
                        handleCheckAnswerButtonVisibility()
                    }
                    PuzzleAction.ShowHintsDialog -> {
                        uiMessageManager.emitMessage(UiMessage(PuzzleUiMessage.ShowHintsDialog))
                    }
                    PuzzleAction.RequestShowRewordAd -> {
                        uiMessageManager.emitMessage(
                            UiMessage(PuzzleUiMessage.ShowRewardAd)
                        )
                    }
                    is PuzzleAction.ShowRewordAd -> {
                        adManager.showRewardAd(
                            activity = action.activity,
                        ) {
                            viewModelScope.launch {
                                puzzleHints.update { it ->
                                    it.apply {
                                        val indexOfFirstInvisible =
                                            indexOfFirst { it.isVisible.not() }
                                        set(
                                            indexOfFirstInvisible,
                                            get(indexOfFirstInvisible).copy(isVisible = true)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun handleCheckAnswerButtonVisibility() {
        state.value.puzzle?.let { puzzle ->
            val checkedPairs: MutableList<Pair<String, String>> = ArrayList()

            puzzle.tables.forEach { table -> checkedPairs.addAll(table.getCheckedPairs()) }

            if (checkedPairs.size >= puzzle.correctPairs.size) {
                isCheckAnswerVisible.emit(true)
            } else {
                isCheckAnswerVisible.emit(false)
            }
        }
    }

    private suspend fun checkAnswers() {
        puzzle.value?.let { puzzle ->
            val checkedPairs: MutableList<Pair<String, String>> = ArrayList()

            puzzle.tables.forEach { table -> checkedPairs.addAll(table.getCheckedPairs()) }

            val answers = state.value.puzzle?.correctPairs?.map { answer ->
                checkedPairs.any { it.compare(answer) }
            }

            if (answers!!.any { it.not() }) {
                uiMessageManager.emitMessage(UiMessage(PuzzleUiMessage.ShowPuzzleErrorDialog))
            } else {
                puzzleName.findNext()?.let {
                    puzzleRepo.makeAvailable(it)
                }
                puzzleRepo.finishPuzzle(puzzleName)
                uiMessageManager.emitMessage(UiMessage(PuzzleUiMessage.ShowWinDialog))
            }

        }
    }

    private suspend fun handleCellClick(action: PuzzleAction.OnCell) {

        val puz = state.value.puzzle?.tables?.toMutableList()?.apply {
            val table = action.table.copy(
                cells = action.table.cells
                    .map { cell ->
                        if (cell === action.cell && cell.filledAutomatically.not()) {
                            return@map cell.copy(state = cell.reduceState())
                        }
                        cell
                    }.map { cell ->
                        if (
                            cell.titleHorizontal == action.cell.titleHorizontal
                            || cell.titleVertical == action.cell.titleVertical
                            && cell.index != action.cell.index
                        ) {
                            if (
                                cell.filledAutomatically
                                && action.cell.reduceState() == Table.Cell.State.EMPTY
                            ) {
                                return@map cell.copy(
                                    state = Table.Cell.State.EMPTY,
                                    filledAutomatically = false
                                )
                            }
                        }
                        cell
                    }.toMutableList()
            )
            set(indexOf(action.table), table)
        }
        puzzle.emit(state.value.puzzle?.copy(tables = puz!!))
    }

    private suspend fun handleTitleHint(action: PuzzleAction.OnCell) {
        if (action.cell.reduceState() != Table.Cell.State.EMPTY) {
            hintedTitles.emit(action.cell.titleHorizontal to action.cell.titleVertical)
        } else {
            hintedTitles.emit(null)
        }
    }

    private fun List<Table.Cell>.markIncorrectCells(table: Table): MutableList<Table.Cell> {
        return map { cell ->
            val horizontalCheckTitles =
                table.cells.filter { it.state == Table.Cell.State.CORRECT }.map {
                    it.titleHorizontal
                }

            val verticalCheckTitles =
                table.cells.filter { it.state == Table.Cell.State.CORRECT }.map {
                    it.titleVertical
                }


            if (cell.state != Table.Cell.State.CORRECT) {
                if (cell.titleVertical in verticalCheckTitles || cell.titleHorizontal in horizontalCheckTitles) {
                    if (cell.state == Table.Cell.State.EMPTY || cell.state == Table.Cell.State.INCORRECT ) {
                        if (cell.filledAutomatically.not() && cell.state != Table.Cell.State.INCORRECT || cell.filledAutomatically && cell.state == Table.Cell.State.INCORRECT){
                            return@map cell.copy(
                                state = Table.Cell.State.INCORRECT,
                                filledAutomatically = true
                            )
                        }
                    }
                }
            }
            cell

        }.toMutableList()
    }

    private suspend fun loadPuzzle(name: PuzzleName) {
        puzzleRepo.getPuzzle(name)
            .flowOn(Dispatchers.IO)
            .collect(puzzle::emit)
    }

    fun submitAction(action: PuzzleAction) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    fun clearMessage(id: Long) {
        viewModelScope.launch {
            uiMessageManager.clearMessage(id)
        }
    }
}




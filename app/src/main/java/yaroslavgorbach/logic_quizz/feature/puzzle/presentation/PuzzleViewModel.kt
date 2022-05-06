package yaroslavgorbach.logic_quizz.feature.puzzle.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import yaroslavgorbach.logic_quizz.data.puzzle.model.Puzzle
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.common.PuzzleRepo
import yaroslavgorbach.logic_quizz.data.puzzle.model.table.Table
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleAction
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleViewState
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesAction
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesUiMessage
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesViewState
import yaroslavgorbach.logic_quizz.utills.UiMessageManager
import javax.inject.Inject

@HiltViewModel
class PuzzleViewModel @Inject constructor(
    private val puzzleRepo: PuzzleRepo
) : ViewModel() {

    private val pendingActions = MutableSharedFlow<PuzzleAction>()

    private var puzzle: MutableStateFlow<Puzzle?> = MutableStateFlow(null)

    private var hintedTitles: MutableStateFlow<Pair<String, String>?> = MutableStateFlow(null)

    private val uiMessageManager: UiMessageManager<PuzzlesUiMessage> = UiMessageManager()

    val state: StateFlow<PuzzleViewState> = combine(
        puzzle,
        hintedTitles,
        uiMessageManager.message,
    ) { puzzle, hintedTitles, message ->
        PuzzleViewState(puzzle = puzzle?.copy(tables = puzzle.tables.map { table ->
            table.copy(cells = table.cells.markIncorrectCells(table))
        }), message = message, hintedTitles = hintedTitles)
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = PuzzleViewState.Test
    )

    init {
        viewModelScope.launch {
            loadPuzzle()
        }

        viewModelScope.launch {
            pendingActions.collect { action ->
                when (action) {
                    is PuzzleAction.OnCell -> {
                        handleTitleHint(action)
                        handleCellClick(action)
                    }
                }
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

    private suspend fun loadPuzzle() {
        puzzleRepo.getPuzzle(PuzzleName.SNACK_TIME)
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




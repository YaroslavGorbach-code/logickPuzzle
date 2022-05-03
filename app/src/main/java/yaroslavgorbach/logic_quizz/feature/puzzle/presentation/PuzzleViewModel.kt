package yaroslavgorbach.logic_quizz.feature.puzzle.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import yaroslavgorbach.logic_quizz.data.puzzle.Puzzle
import yaroslavgorbach.logic_quizz.data.puzzle.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzle.PuzzleRepo
import yaroslavgorbach.logic_quizz.data.puzzle.table.Table
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleAction
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleUiMessage
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleViewState
import yaroslavgorbach.logic_quizz.utills.UiMessageManager
import javax.inject.Inject

@HiltViewModel
class PuzzleViewModel @Inject constructor(
    private val puzzleRepo: PuzzleRepo
) : ViewModel() {

    private val pendingActions = MutableSharedFlow<PuzzleAction>()

    private var puzzle: MutableStateFlow<Puzzle?> = MutableStateFlow(null)

    private val uiMessageManager: UiMessageManager<PuzzleUiMessage> = UiMessageManager()

    val state: StateFlow<PuzzleViewState> = combine(
        puzzle,
        uiMessageManager.message,
    ) { puzzle, message ->
        Log.i("dsdads", "currentPuzzle.tables.toString()")
        PuzzleViewState(puzzle = puzzle, message = message)
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

                        val puz = state.value.puzzle?.tables?.toMutableList()?.apply {
                            val index = indexOf(action.table)
                            val table = action.table.copy(
                                cells = action.table.cells.map {
                                    if (it === action.cell){
                                        it.copy(state = it.reduceState())
                                    }else{
                                        it
                                    }
                                }.toMutableList()
                            )
                            set(index, table)
                        }

                        puzzle.emit(state.value.puzzle?.copy(emptyList(), puz!!))

                    }
//                        puzzle.update { puzzle ->
//                            puzzle?.tables = puzzle?.tables?.apply {
//                                val indexTable = indexOf(action.table)
//
//                                val newCells = action.table.cells.apply {
//                                    val indexCell = indexOf(action.cell)
//
//                                    set(
//                                        indexCell,
//                                        action.cell.copy(state = action.cell.reduceState())
//                                    )
//                                }
//
//                                val newTable = action.table
//                                newTable.cells = newCells
//                                set(indexTable, newTable)
//                            }!!
//                            puzzle
//                        }
                }
            }
        }
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




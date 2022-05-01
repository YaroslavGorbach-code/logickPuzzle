package yaroslavgorbach.logic_quizz.feature.puzzle.presentation

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

                    else -> {

                    }
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




package yaroslavgorbach.logic_quizz.feature.puzzles.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import yaroslavgorbach.logic_quizz.data.common.PuzzleRepo
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesAction
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesUiMessage
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesViewState
import yaroslavgorbach.logic_quizz.utills.UiMessageManager
import javax.inject.Inject

@HiltViewModel
class PuzzlesViewModel @Inject constructor(
    private val puzzleRepo: PuzzleRepo
) : ViewModel() {

    private val pendingActions = MutableSharedFlow<PuzzlesAction>()

    private val uiMessageManager: UiMessageManager<PuzzlesUiMessage> = UiMessageManager()

    val state: StateFlow<PuzzlesViewState> = combine(
        puzzleRepo.getPuzzleItems(),
        uiMessageManager.message,
    ) { puzzles, message ->
        PuzzlesViewState(puzzleItems = puzzles, message = message)
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = PuzzlesViewState.Test
    )

    fun submitAction(action: PuzzlesAction) {
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




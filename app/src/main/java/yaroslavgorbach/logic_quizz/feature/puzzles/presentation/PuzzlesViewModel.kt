package yaroslavgorbach.logic_quizz.feature.puzzles.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import yaroslavgorbach.logic_quizz.data.common.PuzzleRepo
import yaroslavgorbach.logic_quizz.data.puzzles.model.PuzzleItem
import yaroslavgorbach.logic_quizz.data.settings.repo.RepoSettings
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesAction
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesUiMessage
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesViewState
import yaroslavgorbach.logic_quizz.utills.AdManager
import yaroslavgorbach.logic_quizz.utills.UiMessage
import yaroslavgorbach.logic_quizz.utills.UiMessageManager
import javax.inject.Inject

@HiltViewModel
class PuzzlesViewModel @Inject constructor(
    private val puzzleRepo: PuzzleRepo,
    private val puzzleSettings: RepoSettings,
    private val adManager: AdManager
) : ViewModel() {

    private val pendingActions = MutableSharedFlow<PuzzlesAction>()

    private val uiMessageManager: UiMessageManager<PuzzlesUiMessage> = UiMessageManager()

    private val puzzles: MutableStateFlow<List<PuzzleItem>> = MutableStateFlow(emptyList())

    val state: StateFlow<PuzzlesViewState> = combine(
        puzzles,
        uiMessageManager.message,
    ) { puzzles, message ->
        PuzzlesViewState(puzzleItems = puzzles, message = message)
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = PuzzlesViewState.Test
    )

    init {
        viewModelScope.launch {
            puzzleSettings.observeIsFirstAppOpen().collect { isFirstOpen ->
                if (isFirstOpen){
                    uiMessageManager.emitMessage(UiMessage(PuzzlesUiMessage.ShowHelpDialog))
                    puzzleSettings.changeIsFirsAppOpen(false)
                }
            }
        }

        adManager.loadRewordAd()

        viewModelScope.launch {
            pendingActions.collect { action ->
                when (action) {
                    is PuzzlesAction.ShowPuzzleUnAvailableDialog -> {
                        uiMessageManager.emitMessage(
                            UiMessage(
                                PuzzlesUiMessage.ShowPuzzleUnAvailableDialog(
                                    action.name
                                )
                            )
                        )
                    }
                    PuzzlesAction.LoadPuzzle -> {
                        loadPuzzles()
                    }
                    is PuzzlesAction.RequestShowRewordAd -> {
                        uiMessageManager.emitMessage(
                            UiMessage(
                                PuzzlesUiMessage.ShowRewardAd(
                                    action.puzzleName
                                )
                            )
                        )
                    }
                    is PuzzlesAction.ShowRewordAd -> {
                        adManager.showRewardAd(
                            activity = action.activity,
                        ) {
                            viewModelScope.launch {
                                puzzleRepo.makeAvailable(action.puzzleName)
                                loadPuzzles()
                            }
                        }
                    }

                    PuzzlesAction.ShowHelpDialog -> {
                        uiMessageManager.emitMessage(UiMessage(PuzzlesUiMessage.ShowHelpDialog))
                    }
                }
            }
        }
    }

    private suspend fun loadPuzzles() {
        puzzleRepo.getPuzzleItems().collect(puzzles::emit)
    }

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




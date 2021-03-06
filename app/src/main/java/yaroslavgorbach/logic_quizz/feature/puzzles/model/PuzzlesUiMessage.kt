package yaroslavgorbach.logic_quizz.feature.puzzles.model

import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

sealed class PuzzlesUiMessage {
    class ShowPuzzleUnAvailableDialog(val name: PuzzleName): PuzzlesUiMessage()
    data class ShowRewardAd(val puzzleName: PuzzleName) : PuzzlesUiMessage()
    object ShowHelpDialog : PuzzlesUiMessage()
}
package yaroslavgorbach.logic_quizz.feature.puzzle.model

sealed class PuzzleUiMessage {
    object ShowWinDialog: PuzzleUiMessage()
    object ShowPuzzleErrorDialog: PuzzleUiMessage()
    object ShowStoryDialog: PuzzleUiMessage()
    object ShowHintsDialog: PuzzleUiMessage()
    object ShowAnswersDialog: PuzzleUiMessage()
    class ShowRewardAd(val resultAction: RewordResultAction): PuzzleUiMessage()
}
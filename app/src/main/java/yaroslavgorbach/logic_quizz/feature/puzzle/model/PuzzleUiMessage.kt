package yaroslavgorbach.logic_quizz.feature.puzzle.model

sealed class PuzzleUiMessage {
    object ShowWinDialog: PuzzleUiMessage()
    object ShowPuzzleErrorDialog: PuzzleUiMessage()
}
package yaroslavgorbach.logic_quizz.feature.puzzles.model

import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

sealed class PuzzlesAction {
    class ShowPuzzleUnAvailableDialog(val name: PuzzleName): PuzzlesAction()
}
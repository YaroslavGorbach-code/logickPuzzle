package yaroslavgorbach.logic_quizz.feature.puzzles.model

import android.app.Activity
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

sealed class PuzzlesAction {
    class ShowPuzzleUnAvailableDialog(val name: PuzzleName): PuzzlesAction()
    object LoadPuzzle: PuzzlesAction()
    data class ShowRewordAd(val activity: Activity, val puzzleName: PuzzleName) : PuzzlesAction()
    data class RequestShowRewordAd(val puzzleName: PuzzleName) : PuzzlesAction()
}
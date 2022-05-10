package yaroslavgorbach.logic_quizz.feature.puzzle.model

import android.app.Activity
import yaroslavgorbach.logic_quizz.data.puzzle.model.table.Table

sealed class PuzzleAction {
    class OnCell(val table: Table, val cell: Table.Cell) : PuzzleAction()
    object CheckAnswer : PuzzleAction()
    object TableUpdated : PuzzleAction()
    object ShowHintsDialog : PuzzleAction()
    object RequestShowRewordAd : PuzzleAction()
    class ShowRewordAd(val activity: Activity) : PuzzleAction()
}
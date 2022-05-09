package yaroslavgorbach.logic_quizz.data.puzzle.factory

import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

class TableCellSizeProvider() {

    fun provide(puzzleName: PuzzleName): Int {
        return when (puzzleName) {
            PuzzleName.SNACK_TIME -> 40
            PuzzleName.MATES_PLUS_DATES -> 40
            PuzzleName.MORE_PAINTERS -> 30
        }
    }
}
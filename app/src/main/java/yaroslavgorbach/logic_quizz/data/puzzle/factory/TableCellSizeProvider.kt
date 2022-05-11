package yaroslavgorbach.logic_quizz.data.puzzle.factory

import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

class TableCellSizeProvider() {

    fun provide(puzzleName: PuzzleName): Int {
        return when (puzzleName) {
            PuzzleName.SNACK_TIME -> 40
            PuzzleName.MATES_PLUS_DATES -> 40
            PuzzleName.MORE_PAINTERS -> 30
            PuzzleName.KITTENS_AND_KIDS -> 30
            PuzzleName.JAZZ_BANDS_SOLOS -> 30
            PuzzleName.TRAINING_PUZZLE -> 40
            PuzzleName.MULTICOLOUR_DOORS -> 40
            PuzzleName.WHO_ATE_WHICH_FRUIT -> 30
        }
    }
}
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
            PuzzleName.DRACONIA_TRAINERS -> 30
            PuzzleName.JUST_A_THOUGHT -> 40
            PuzzleName.FAMILY_TRIPS -> 30
            PuzzleName.JUNGLE_GYM_HOOPLA -> 30
            PuzzleName.HOGWARTS ->  30
            PuzzleName.SANDBOX_DISASTER -> 30
            PuzzleName.PAINTBALLING_WEEKEND -> 30
            PuzzleName.WHY -> 30
            PuzzleName.OFFICE_ORDER -> 30
            PuzzleName.MURDER_AT_BRAINTASER -> 30
            PuzzleName.MOVING_TO_LONDON -> 30
            PuzzleName.NIGHTY_NIGHT -> 30
            PuzzleName.MISS_BROWN_MURDER -> 30
        }
    }
}
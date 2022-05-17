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
            PuzzleName.NEVER_ASK_A_WOMAN_HEE_AGE -> 30
            PuzzleName.COMMUTER_PROBLEMS -> 30
            PuzzleName.WORLD_DOMINATION -> 30
            PuzzleName.FORTUNE_TELLER -> 30
            PuzzleName.LAST_YEAR_GIFTS -> 30
            PuzzleName.PAYDAY -> 30
            PuzzleName.FLOUR_POWER -> 30
            PuzzleName.ON_THE_CANAL -> 25
            PuzzleName.SECRET_IN_STONE -> 30
            PuzzleName.BOUND_FOR_CANADA -> 30
            PuzzleName.VANISHING_ACTORS -> 30
            PuzzleName.APPLE_PICKERS -> 30
            PuzzleName.LATE_AT_THE_LAKE -> 30
            PuzzleName.BALLROOM_DANCING ->  30
            PuzzleName.SNAIL_RACES -> 30
            PuzzleName.LOST_PROPERTY -> 30
        }
    }
}
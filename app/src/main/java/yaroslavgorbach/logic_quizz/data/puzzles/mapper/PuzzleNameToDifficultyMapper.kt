package yaroslavgorbach.logic_quizz.data.puzzles.mapper

import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzles.model.DifficultLevel

class PuzzleNameToDifficultyMapper() {

    fun map(name: PuzzleName): DifficultLevel {
        return when (name) {
            PuzzleName.SNACK_TIME -> DifficultLevel.ONE
            PuzzleName.MATES_PLUS_DATES -> DifficultLevel.ONE
            PuzzleName.MULTICOLOUR_DOORS -> DifficultLevel.ONE
            PuzzleName.WHO_ATE_WHICH_FRUIT -> DifficultLevel.ONE
            PuzzleName.MORE_PAINTERS -> DifficultLevel.THREE
            PuzzleName.KITTENS_AND_KIDS -> DifficultLevel.THREE
            PuzzleName.JAZZ_BANDS_SOLOS -> DifficultLevel.FIVE
            PuzzleName.TRAINING_PUZZLE -> DifficultLevel.TRAIN
            PuzzleName.DRACONIA_TRAINERS ->  DifficultLevel.ONE
            PuzzleName.JUST_A_THOUGHT -> DifficultLevel.TWO
            PuzzleName.FAMILY_TRIPS ->  DifficultLevel.TWO
            PuzzleName.JUNGLE_GYM_HOOPLA ->  DifficultLevel.TWO
            PuzzleName.HOGWARTS -> DifficultLevel.TWO
            PuzzleName.SANDBOX_DISASTER ->  DifficultLevel.TWO
            PuzzleName.PAINTBALLING_WEEKEND -> DifficultLevel.TWO
            PuzzleName.WHY -> DifficultLevel.TWO
            PuzzleName.OFFICE_ORDER -> DifficultLevel.TWO
            PuzzleName.MURDER_AT_BRAINTASER -> DifficultLevel.THREE
            PuzzleName.MOVING_TO_LONDON -> DifficultLevel.THREE
            PuzzleName.NIGHTY_NIGHT -> DifficultLevel.THREE
            PuzzleName.MISS_BROWN_MURDER -> DifficultLevel.THREE
            PuzzleName.NEVER_ASK_A_WOMAN_HEE_AGE -> DifficultLevel.THREE
            PuzzleName.COMMUTER_PROBLEMS -> DifficultLevel.THREE
            PuzzleName.WORLD_DOMINATION -> DifficultLevel.FOUR
            PuzzleName.FORTUNE_TELLER -> DifficultLevel.FOUR
            PuzzleName.LAST_YEAR_GIFTS -> DifficultLevel.FOUR
            PuzzleName.PAYDAY -> DifficultLevel.FOUR
            PuzzleName.FLOUR_POWER -> DifficultLevel.FOUR
            PuzzleName.ON_THE_CANAL -> DifficultLevel.FOUR
            PuzzleName.SECRET_IN_STONE -> DifficultLevel.FIVE
            PuzzleName.BOUND_FOR_CANADA -> DifficultLevel.FIVE
            PuzzleName.VANISHING_ACTORS -> DifficultLevel.FIVE
            PuzzleName.APPLE_PICKERS -> DifficultLevel.FIVE
            PuzzleName.LATE_AT_THE_LAKE -> DifficultLevel.FIVE
            PuzzleName.BALLROOM_DANCING -> DifficultLevel.FIVE
            PuzzleName.SNAIL_RACES -> DifficultLevel.SIX
            PuzzleName.LOST_PROPERTY -> DifficultLevel.SIX
            PuzzleName.NIGHT_LIGHT -> DifficultLevel.SIX
            PuzzleName.BAT_ATTACK -> DifficultLevel.SIX
        }
    }
}
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
            PuzzleName.TRAINING_PUZZLE -> DifficultLevel.ONE
            PuzzleName.DRACONIA_TRAINERS ->  DifficultLevel.ONE
            PuzzleName.JUST_A_THOUGHT -> DifficultLevel.TWO
            PuzzleName.FAMILY_TRIPS ->  DifficultLevel.TWO
            PuzzleName.JUNGLE_GYM_HOOPLA ->  DifficultLevel.TWO
            PuzzleName.HOGWARTS -> DifficultLevel.TWO
            PuzzleName.SANDBOX_DISASTER ->  DifficultLevel.TWO
        }
    }
}
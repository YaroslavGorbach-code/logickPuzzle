package yaroslavgorbach.logic_quizz.data.puzzles.mapper

import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzles.model.DifficultLevel

class PuzzleNameToDifficultyMapper() {

    fun map(name: PuzzleName): DifficultLevel {
        return when (name) {
            PuzzleName.SNACK_TIME -> DifficultLevel.ONE
            PuzzleName.MATES_PLUS_DATES -> DifficultLevel.ONE
            PuzzleName.MORE_PAINTERS -> DifficultLevel.THREE
        }
    }
}
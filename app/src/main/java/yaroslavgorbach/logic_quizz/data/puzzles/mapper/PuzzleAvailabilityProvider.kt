package yaroslavgorbach.logic_quizz.data.puzzles.mapper

import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

class PuzzleAvailabilityProvider {
    fun isAvailable(puzzleName: PuzzleName): Boolean {
        return when (puzzleName) {
            PuzzleName.SNACK_TIME -> true
            PuzzleName.MATES_PLUS_DATES -> true
        }
    }
}
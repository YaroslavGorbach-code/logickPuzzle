package yaroslavgorbach.logic_quizz.data.puzzles.factory

import kotlinx.coroutines.flow.first
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzles.PuzzleDataStore

class PuzzleAvailabilityProvider(private val puzzleDataStore: PuzzleDataStore) {
    suspend fun isAvailable(puzzleName: PuzzleName): Boolean {
        return when (puzzleName) {
//            PuzzleName.SNACK_TIME -> true
//            else -> puzzleDataStore.isAvailable(puzzleName).first()
            else -> true
        }
    }
}
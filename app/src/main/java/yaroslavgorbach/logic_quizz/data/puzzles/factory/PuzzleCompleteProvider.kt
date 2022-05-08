package yaroslavgorbach.logic_quizz.data.puzzles.factory

import kotlinx.coroutines.flow.first
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzles.PuzzleDataStore

class PuzzleCompleteProvider(private val puzzleDataStore: PuzzleDataStore) {
    suspend fun isCompleted(puzzleName: PuzzleName): Boolean {
        return puzzleDataStore.isFinished(puzzleName).first()
    }
}
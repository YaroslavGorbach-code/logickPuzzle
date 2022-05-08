package yaroslavgorbach.logic_quizz.data.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzle.factory.PuzzleFactory
import yaroslavgorbach.logic_quizz.data.puzzle.model.Puzzle
import yaroslavgorbach.logic_quizz.data.puzzles.PuzzleDataStore
import yaroslavgorbach.logic_quizz.data.puzzles.factory.PuzzlesFactory
import yaroslavgorbach.logic_quizz.data.puzzles.model.PuzzleItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PuzzleRepoImp @Inject constructor(
    private val puzzleFactory: PuzzleFactory,
    private val puzzlesFactory: PuzzlesFactory,
    private val puzzleDataStore: PuzzleDataStore
) : PuzzleRepo {

    override fun getPuzzle(puzzleName: PuzzleName): Flow<Puzzle> {
        return flowOf(puzzleFactory.create(puzzleName))
    }

    override suspend fun getPuzzleItems(): Flow<List<PuzzleItem>> {
        return flowOf(puzzlesFactory.create())
    }

    override suspend fun makeAvailable(name: PuzzleName) {
        puzzleDataStore.makeAvailable(name)
    }

    override suspend fun finishPuzzle(name: PuzzleName) {
        puzzleDataStore.finishPuzzle(name)
    }
}
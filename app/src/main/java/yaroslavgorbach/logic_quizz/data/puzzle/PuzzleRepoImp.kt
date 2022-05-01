package yaroslavgorbach.logic_quizz.data.puzzle

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import yaroslavgorbach.logic_quizz.data.puzzle.factory.PuzzleFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PuzzleRepoImp @Inject constructor(private val puzzleFactory: PuzzleFactory) : PuzzleRepo {

    override fun getPuzzle(puzzleName: PuzzleName): Flow<Puzzle> {
        return flowOf(puzzleFactory.create(puzzleName))
    }
}
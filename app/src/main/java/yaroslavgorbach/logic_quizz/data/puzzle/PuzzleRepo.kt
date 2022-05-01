package yaroslavgorbach.logic_quizz.data.puzzle

import kotlinx.coroutines.flow.Flow


interface PuzzleRepo {
    fun getPuzzle(puzzleName: PuzzleName): Flow<Puzzle>
}
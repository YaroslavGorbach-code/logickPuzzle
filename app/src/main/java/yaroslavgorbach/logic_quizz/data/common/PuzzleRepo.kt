package yaroslavgorbach.logic_quizz.data.common

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzle.model.Puzzle
import yaroslavgorbach.logic_quizz.data.puzzles.model.PuzzleItem


interface PuzzleRepo {
    fun getPuzzle(puzzleName: PuzzleName): Flow<Puzzle>
    fun getPuzzleItems(): Flow<List<PuzzleItem>>
}
package yaroslavgorbach.logic_quizz.data.puzzle.model

import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzle.model.table.Table
import yaroslavgorbach.logic_quizz.data.puzzle.model.table.TableTitle

data class Puzzle(
    val titles: List<TableTitle>,
    val tables: List<Table>,
    val clues: List<String>,
    val correctPairs: List<Pair<String, String>>,
    val name: PuzzleName
) {
    val numberOfRows: Int
        get() = tables.map { it.indexInPuzzleVertical }.maxOrNull()?.plus(1) ?: 0

    val numberOfColumns
        get() = tables.map { it.indexInPuzzleHorizontal }.maxOrNull()?.plus(1) ?: 0
}


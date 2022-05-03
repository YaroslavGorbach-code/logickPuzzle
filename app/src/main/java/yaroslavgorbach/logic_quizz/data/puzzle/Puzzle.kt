package yaroslavgorbach.logic_quizz.data.puzzle

import yaroslavgorbach.logic_quizz.data.puzzle.table.Table
import yaroslavgorbach.logic_quizz.data.puzzle.table.TableTitle

data class Puzzle(val titles: List<TableTitle>, val tables: List<Table>) {
    val numberOfRows: Int
        get() = tables.map { it.indexInPuzzleVertical }.maxOrNull()?.plus(1) ?: 0

    val numberOfColumns
        get() = tables.map { it.indexInPuzzleHorizontal }.maxOrNull()?.plus(1) ?: 0
}


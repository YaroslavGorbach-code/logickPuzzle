package yaroslavgorbach.logic_quizz.data.puzzle

import android.util.Log
import yaroslavgorbach.logic_quizz.data.puzzle.table.Table
import yaroslavgorbach.logic_quizz.data.puzzle.table.TableTitle

data class Puzzle(val titles: List<TableTitle>) {

    private val horizontalTitles: MutableList<TableTitle> =
        titles.filter { it.orientation == TableTitle.Orientation.HORIZONTAL }.toMutableList()

    private val verticalTitles: List<TableTitle> =
        titles.filter { it.orientation == TableTitle.Orientation.VERTICAL }

    val tables: MutableList<Table> = ArrayList()

    val numberOfRows: Int
        get() = tables.map { it.indexInPuzzleVertical }.maxOrNull()?.plus(1) ?: 0

    val numberOfColumns
        get() = tables.map { it.indexInPuzzleHorizontal }.maxOrNull()?.plus(1) ?: 0

    init {
        createTables()
    }

    private fun createTables() {
        verticalTitles.forEachIndexed { indexVertical, verticalTitle ->
            horizontalTitles.forEachIndexed { indexHorizontal, horizontalTitle ->
                tables.add(
                    Table(
                        width = horizontalTitle.items.size,
                        height = verticalTitle.items.size,
                        titleHorizontal = horizontalTitle,
                        titleVertical = verticalTitle,
                        indexInPuzzleVertical = indexVertical,
                        indexInPuzzleHorizontal = indexHorizontal,
                    )
                )
            }
            horizontalTitles.removeLast()
        }
    }
}


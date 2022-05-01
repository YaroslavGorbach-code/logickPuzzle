package yaroslavgorbach.logic_quizz.data.puzzle

import yaroslavgorbach.logic_quizz.data.puzzle.table.Table
import yaroslavgorbach.logic_quizz.data.puzzle.table.TableTitle

data class Puzzle(val titles: List<TableTitle>) {

    private val horizontalTitles: MutableList<TableTitle> =
        titles.filter { it.orientation == TableTitle.Orientation.HORIZONTAL }.toMutableList()

    private val verticalTitles: List<TableTitle> =
        titles.filter { it.orientation == TableTitle.Orientation.VERTICAL }

    val tables: MutableList<Table> = ArrayList()

    init {
        createTables()
    }

    private fun createTables() {
        verticalTitles.forEach { verticalTitle ->
            horizontalTitles.forEach { horizontalTitle ->
                tables.add(
                    Table(
                        width = horizontalTitle.items.size,
                        height = verticalTitle.items.size,
                        titleHorizontal = horizontalTitle,
                        titleVertical = verticalTitle
                    )
                )
            }
            horizontalTitles.removeLast()
        }
    }
}


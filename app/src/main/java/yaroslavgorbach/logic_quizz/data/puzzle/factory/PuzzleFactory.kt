package yaroslavgorbach.logic_quizz.data.puzzle.factory

import yaroslavgorbach.logic_quizz.data.puzzle.Puzzle
import yaroslavgorbach.logic_quizz.data.puzzle.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzle.table.Table
import yaroslavgorbach.logic_quizz.data.puzzle.table.TableTitle

class PuzzleFactory(private val puzzleTitleFactory: PuzzleTitlesFactory) {

    fun create(name: PuzzleName): Puzzle {
        val titles = puzzleTitleFactory.create(name)

        val horizontalTitles: MutableList<TableTitle> =
            titles.filter { it.orientation == TableTitle.Orientation.HORIZONTAL }.toMutableList()

        val verticalTitles: List<TableTitle> =
            titles.filter { it.orientation == TableTitle.Orientation.VERTICAL }

        val tables: MutableList<Table> = ArrayList()

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
        return Puzzle(titles, tables)
    }
}
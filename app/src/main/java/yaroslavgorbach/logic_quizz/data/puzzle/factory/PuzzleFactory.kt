package yaroslavgorbach.logic_quizz.data.puzzle.factory

import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzle.model.Puzzle
import yaroslavgorbach.logic_quizz.data.puzzle.model.table.Table
import yaroslavgorbach.logic_quizz.data.puzzle.model.table.TableTitle

class PuzzleFactory(
    private val puzzleTitleFactory: PuzzleTitlesFactory,
    private val puzzleCluesProvider: PuzzleCluesProvider,
    private val correctPairsProvider: CorrectPairsProvider,
    private val puzzleStoryProvider: PuzzleStoryProvider
) {

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

        return Puzzle(
            titles,
            tables,
            puzzleCluesProvider.provide(name),
            correctPairsProvider.provide(name),
            name,
            puzzleStoryProvider.provide(name)
        )
    }
}
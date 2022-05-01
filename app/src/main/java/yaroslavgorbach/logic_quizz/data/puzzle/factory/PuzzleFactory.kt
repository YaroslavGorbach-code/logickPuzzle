package yaroslavgorbach.logic_quizz.data.puzzle.factory

import android.content.Context
import yaroslavgorbach.logic_quizz.R
import yaroslavgorbach.logic_quizz.data.puzzle.Puzzle
import yaroslavgorbach.logic_quizz.data.puzzle.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzle.table.TableTitle

class PuzzleFactory(val context: Context) {

    fun create(puzzleName: PuzzleName): Puzzle {
        when (puzzleName) {
            PuzzleName.SNACK_TIME -> {
                return Puzzle(
                    titles = listOf(
                        TableTitle(
                            name = context.getString(R.string.snack_time_items_1_name),
                            items = context.resources.getStringArray(R.array.snack_time_items_1).toList(),
                            orderNumber = 1,
                            orientation = TableTitle.Orientation.VERTICAL
                        ),
                        TableTitle(
                            name = context.getString(R.string.snack_time_items_2_name),
                            items = context.resources.getStringArray(R.array.snack_time_items_2).toList(),
                            orderNumber = 2,
                            orientation = TableTitle.Orientation.VERTICAL
                        ),
                        TableTitle(
                            name = context.getString(R.string.snack_time_items_3_name),
                            items = context.resources.getStringArray(R.array.snack_time_items_3).toList(),
                            orderNumber = 1,
                            orientation = TableTitle.Orientation.HORIZONTAL
                        ),
                        TableTitle(
                            name = context.getString(R.string.snack_time_items_2_name),
                            items = context.resources.getStringArray(R.array.snack_time_items_2).toList(),
                            orderNumber = 2,
                            orientation = TableTitle.Orientation.HORIZONTAL
                        )
                    )
                )
            }
        }
    }
}
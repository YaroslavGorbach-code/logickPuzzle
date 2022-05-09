package yaroslavgorbach.logic_quizz.data.puzzle.factory

import android.content.Context
import yaroslavgorbach.logic_quizz.R
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

class PuzzleCluesProvider(private val context: Context) {

    fun provide(puzzleName: PuzzleName): List<String> {
        return when (puzzleName) {
            PuzzleName.SNACK_TIME -> {
                context.resources.getStringArray(R.array.snack_time_clues).toList()
            }
            PuzzleName.MATES_PLUS_DATES -> {
                context.resources.getStringArray(R.array.mates_plus_dates_clues).toList()
            }
            PuzzleName.MORE_PAINTERS -> {
                context.resources.getStringArray(R.array.more_painters_clues).toList()
            }
        }
    }
}
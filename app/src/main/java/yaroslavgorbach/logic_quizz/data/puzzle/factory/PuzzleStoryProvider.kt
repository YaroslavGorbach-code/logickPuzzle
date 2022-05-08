package yaroslavgorbach.logic_quizz.data.puzzle.factory

import android.content.Context
import yaroslavgorbach.logic_quizz.R
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

class PuzzleStoryProvider(private val context: Context) {

    fun provide(puzzleName: PuzzleName): String {
        return when (puzzleName) {
            PuzzleName.SNACK_TIME -> {
                context.resources.getString(R.string.snack_time_story)
            }
            PuzzleName.MATES_PLUS_DATES -> {
                context.resources.getString(R.string.mates_plus_dates_story)
            }
        }
    }
}
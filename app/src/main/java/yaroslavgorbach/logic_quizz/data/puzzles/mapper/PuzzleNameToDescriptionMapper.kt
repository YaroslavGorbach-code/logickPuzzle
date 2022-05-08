package yaroslavgorbach.logic_quizz.data.puzzles.mapper

import android.content.Context
import yaroslavgorbach.logic_quizz.R
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

class PuzzleNameToDescriptionMapper(private val context: Context) {

    fun map(name: PuzzleName): String {
        return when (name) {
            PuzzleName.SNACK_TIME -> context.getString(R.string.snack_time_story)
            PuzzleName.MATES_PLUS_DATES -> context.getString(R.string.mates_plus_dates_story)
        }
    }
}
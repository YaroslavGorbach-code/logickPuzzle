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
            PuzzleName.MORE_PAINTERS -> {
                context.resources.getString(R.string.more_painters_story)
            }
            PuzzleName.KITTENS_AND_KIDS -> {
                context.resources.getString(R.string.kittens_and_kids_story)
            }
            PuzzleName.JAZZ_BANDS_SOLOS -> {
                context.resources.getString(R.string.jazz_band_solos_story)
            }
        }
    }
}
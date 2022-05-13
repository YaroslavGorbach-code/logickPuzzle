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
            PuzzleName.TRAINING_PUZZLE -> {
                context.resources.getString(R.string.training_puzzle_story)
            }
            PuzzleName.MULTICOLOUR_DOORS -> {
                context.resources.getString(R.string.multicolour_doors_story)
            }
            PuzzleName.WHO_ATE_WHICH_FRUIT -> {
                context.resources.getString(R.string.who_ate_which_fruit_story)
            }
            PuzzleName.DRACONIA_TRAINERS -> {
                context.resources.getString(R.string.draconia_trainers_story)
            }
            PuzzleName.JUST_A_THOUGHT -> {
                context.resources.getString(R.string.just_a_thought_story)
            }
            PuzzleName.FAMILY_TRIPS -> {
                context.resources.getString(R.string.family_trips_story)
            }
            PuzzleName.JUNGLE_GYM_HOOPLA -> {
                context.resources.getString(R.string.jungle_gym_hoopla_story)
            }
            PuzzleName.HOGWARTS -> {
                context.resources.getString(R.string.hogwarts_story)
            }
            PuzzleName.SANDBOX_DISASTER -> {
                context.resources.getString(R.string.sandbox_disaster_story)
            }
            PuzzleName.PAINTBALLING_WEEKEND -> {
                context.resources.getString(R.string.paintballing_weekend_story)
            }
            PuzzleName.WHY -> {
                context.resources.getString(R.string.why_story)
            }
            PuzzleName.OFFICE_ORDER -> {
                context.resources.getString(R.string.office_order_story)
            }
            PuzzleName.MURDER_AT_BRAINTASER -> {
                context.resources.getString(R.string.murder_at_brainteaser_story)
            }
            PuzzleName.MOVING_TO_LONDON -> {
                context.resources.getString(R.string.moving_to_london_story)
            }
            PuzzleName.NIGHTY_NIGHT -> {
                context.resources.getString(R.string.murder_at_brainteaser_story)
            }
            PuzzleName.MISS_BROWN_MURDER -> {
                context.resources.getString(R.string.miss_brown_murder_story)
            }
            PuzzleName.NEVER_ASK_A_WOMAN_HEE_AGE -> {
                context.resources.getString(R.string.never_ask_a_woman_her_age_story)
            }
        }
    }
}
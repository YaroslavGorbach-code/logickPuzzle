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
            PuzzleName.KITTENS_AND_KIDS -> {
                context.resources.getStringArray(R.array.kittens_and_kids_clues).toList()
            }
            PuzzleName.JAZZ_BANDS_SOLOS -> {
                context.resources.getStringArray(R.array.jazz_band_solos_clues).toList()
            }
            PuzzleName.TRAINING_PUZZLE -> {
                context.resources.getStringArray(R.array.training_puzzle_clues).toList()
            }
            PuzzleName.MULTICOLOUR_DOORS -> {
                context.resources.getStringArray(R.array.multicolour_doors_clues).toList()
            }
            PuzzleName.WHO_ATE_WHICH_FRUIT -> {
                context.resources.getStringArray(R.array.who_ate_which_fruit_clues).toList()
            }
            PuzzleName.DRACONIA_TRAINERS -> {
                context.resources.getStringArray(R.array.draconia_trainers_clues).toList()
            }
            PuzzleName.JUST_A_THOUGHT -> {
                context.resources.getStringArray(R.array.just_a_thought_clues).toList()
            }
            PuzzleName.FAMILY_TRIPS -> {
                context.resources.getStringArray(R.array.family_trips_clues).toList()
            }
            PuzzleName.JUNGLE_GYM_HOOPLA -> {
                context.resources.getStringArray(R.array.jungle_gym_hoopla_clues).toList()
            }
            PuzzleName.HOGWARTS -> {
                context.resources.getStringArray(R.array.hogwarts_clues).toList()
            }
            PuzzleName.SANDBOX_DISASTER -> {
                context.resources.getStringArray(R.array.sandbox_disaster_clues).toList()
            }
            PuzzleName.PAINTBALLING_WEEKEND -> {
                context.resources.getStringArray(R.array.paintballing_weekend_clues).toList()
            }
            PuzzleName.WHY -> {
                context.resources.getStringArray(R.array.why_clues).toList()
            }
            PuzzleName.OFFICE_ORDER -> {
                context.resources.getStringArray(R.array.office_order_clues).toList()
            }
            PuzzleName.MURDER_AT_BRAINTASER -> {
                context.resources.getStringArray(R.array.murder_at_brainteaser_clues).toList()
            }
            PuzzleName.MOVING_TO_LONDON -> {
                context.resources.getStringArray(R.array.moving_to_london_clues).toList()
            }
            PuzzleName.NIGHTY_NIGHT -> {
                context.resources.getStringArray(R.array.nighty_night_clues).toList()
            }
            PuzzleName.MISS_BROWN_MURDER -> {
                context.resources.getStringArray(R.array.miss_brown_murder_clues).toList()
            }
        }
    }
}
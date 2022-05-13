package yaroslavgorbach.logic_quizz.data.puzzle.factory

import android.content.Context
import yaroslavgorbach.logic_quizz.R
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzle.model.Hint

class PuzzleHintsProvider(private val context: Context) {

    fun provide(puzzleName: PuzzleName): List<Hint> {
        return when (puzzleName) {
            PuzzleName.HOGWARTS ->{
                context.resources.getStringArray(R.array.hogwarts_hints).toList()
                    .mapIndexed(::Hint)
            }
            PuzzleName.SNACK_TIME -> {
                context.resources.getStringArray(R.array.snack_time_hints).toList()
                    .mapIndexed(::Hint)
            }
            PuzzleName.MATES_PLUS_DATES -> {
                context.resources.getStringArray(R.array.mates_plus_dates_hints).toList()
                    .mapIndexed(::Hint)
            }
            PuzzleName.JAZZ_BANDS_SOLOS -> {
                context.resources.getStringArray(R.array.jazz_band_solos_hints).toList()
                    .mapIndexed(::Hint)
            }
            PuzzleName.DRACONIA_TRAINERS -> {
                context.resources.getStringArray(R.array.draconia_trainers_hints).toList()
                    .mapIndexed(::Hint)
            }
            PuzzleName.FAMILY_TRIPS -> {
                context.resources.getStringArray(R.array.family_trips_hints).toList()
                    .mapIndexed(::Hint)
            }
            PuzzleName.JUNGLE_GYM_HOOPLA -> {
                context.resources.getStringArray(R.array.jungle_gym_hoopla_hints).toList()
                    .mapIndexed(::Hint)
            }
            PuzzleName.SANDBOX_DISASTER -> {
                context.resources.getStringArray(R.array.sandbox_disaster_hints).toList()
                    .mapIndexed(::Hint)
            }
            PuzzleName.WHY -> {
                context.resources.getStringArray(R.array.why_hints).toList()
                    .mapIndexed(::Hint)
            }
            PuzzleName.MOVING_TO_LONDON -> {
                context.resources.getStringArray(R.array.moving_to_london_hints).toList()
                    .mapIndexed(::Hint)
            }

            PuzzleName.NIGHTY_NIGHT,
            PuzzleName.MORE_PAINTERS,
            PuzzleName.TRAINING_PUZZLE,
            PuzzleName.MULTICOLOUR_DOORS,
            PuzzleName.WHO_ATE_WHICH_FRUIT,
            PuzzleName.JUST_A_THOUGHT,
            PuzzleName.PAINTBALLING_WEEKEND,
            PuzzleName.MURDER_AT_BRAINTASER,
            PuzzleName.OFFICE_ORDER,
            PuzzleName.NEVER_ASK_A_WOMAN_HEE_AGE,
            PuzzleName.MISS_BROWN_MURDER,
            PuzzleName.COMMUTER_PROBLEMS,
            PuzzleName.KITTENS_AND_KIDS -> emptyList()

        }
    }
}
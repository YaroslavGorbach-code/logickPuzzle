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
            PuzzleName.NEVER_ASK_A_WOMAN_HEE_AGE -> {
                context.resources.getStringArray(R.array.never_ask_a_woman_her_age_clues).toList()
            }
            PuzzleName.COMMUTER_PROBLEMS -> {
                context.resources.getStringArray(R.array.commuter_problems_clues).toList()
            }
            PuzzleName.WORLD_DOMINATION -> {
                context.resources.getStringArray(R.array.world_domination_clues).toList()
            }
            PuzzleName.FORTUNE_TELLER -> {
                context.resources.getStringArray(R.array.fortune_teller_name_clues).toList()
            }
            PuzzleName.LAST_YEAR_GIFTS -> {
                context.resources.getStringArray(R.array.last_year_gifts_clues).toList()
            }
            PuzzleName.PAYDAY -> {
                context.resources.getStringArray(R.array.payday_clues).toList()
            }
            PuzzleName.FLOUR_POWER -> {
                context.resources.getStringArray(R.array.flour_power_clues).toList()
            }
            PuzzleName.ON_THE_CANAL -> {
                context.resources.getStringArray(R.array.on_the_canal_clues).toList()
            }
            PuzzleName.SECRET_IN_STONE -> {
                context.resources.getStringArray(R.array.secret_in_stone_clues).toList()
            }
            PuzzleName.BOUND_FOR_CANADA -> {
                context.resources.getStringArray(R.array.bound_for_canada_clues).toList()
            }
            PuzzleName.VANISHING_ACTORS -> {
                context.resources.getStringArray(R.array.vanishing_actors_clues).toList()
            }
            PuzzleName.APPLE_PICKERS -> {
                context.resources.getStringArray(R.array.apple_pickers_clues).toList()
            }
            PuzzleName.LATE_AT_THE_LAKE -> {
                context.resources.getStringArray(R.array.late_at_the_lake_clues).toList()
            }
            PuzzleName.BALLROOM_DANCING ->{
                context.resources.getStringArray(R.array.ballroom_dancing_clues).toList()
            }
            PuzzleName.SNAIL_RACES -> {
                context.resources.getStringArray(R.array.snack_time_clues).toList()
            }
            PuzzleName.LOST_PROPERTY -> {
                context.resources.getStringArray(R.array.lost_property_clues).toList()
            }
            PuzzleName.NIGHT_LIGHT -> {
                context.resources.getStringArray(R.array.nighty_night_clues).toList()
            }
            PuzzleName.BAT_ATTACK -> {
                context.resources.getStringArray(R.array.bat_attack_clues).toList()
            }
        }
    }
}
package yaroslavgorbach.logic_quizz.data.common.model

import yaroslavgorbach.logic_quizz.R

enum class PuzzleName(val resId: Int) {
    // easy
    TRAINING_PUZZLE(R.string.training_puzzle_name),
    SNACK_TIME(R.string.snack_time_name),
    MATES_PLUS_DATES(R.string.mates_plus_dates_name),
    MULTICOLOUR_DOORS(R.string.multicolour_doors_name),
    WHO_ATE_WHICH_FRUIT(R.string.who_ate_which_fruit_name),
    DRACONIA_TRAINERS(R.string.draconia_trainers_name),
    JUST_A_THOUGHT(R.string.just_a_thought_name),
    FAMILY_TRIPS(R.string.family_trips_name),
    JUNGLE_GYM_HOOPLA(R.string.jungle_gym_hoopla_name),
    HOGWARTS(R.string.hogwarts_name),
    SANDBOX_DISASTER(R.string.sandbox_disaster_name),
    PAINTBALLING_WEEKEND(R.string.paintballing_weekend_name),
    WHY(R.string.why_name),
    OFFICE_ORDER(R.string.office_order_name),

    //middle
    MORE_PAINTERS(R.string.more_painters_name),
    KITTENS_AND_KIDS(R.string.kittens_and_kids_name),
    MURDER_AT_BRAINTASER(R.string.murder_at_brainteaser_name),
    MOVING_TO_LONDON(R.string.moving_to_london_name),
    NIGHTY_NIGHT(R.string.nighty_night_name),
    MISS_BROWN_MURDER(R.string.miss_brown_murder_name),
    NEVER_ASK_A_WOMAN_HEE_AGE(R.string.never_ask_a_woman_her_age_name),
    COMMUTER_PROBLEMS(R.string.commuter_problems_name),
    WORLD_DOMINATION(R.string.world_domination_name),
    FORTUNE_TELLER(R.string.fortune_teller_name),
    LAST_YEAR_GIFTS(R.string.last_year_gifts_name),
    PAYDAY(R.string.payday_name),
    FLOUR_POWER(R.string.flour_power_name),

    //hard
    JAZZ_BANDS_SOLOS(R.string.jazz_band_solos_name),
}

fun PuzzleName.findNext(): PuzzleName? {
    val currentIndex = PuzzleName.values().indexOf(this)
    return PuzzleName.values().getOrNull(currentIndex + 1)
}

fun PuzzleName.isLast(): Boolean {
    return this == PuzzleName.values().last()
}
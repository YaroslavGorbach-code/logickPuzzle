package yaroslavgorbach.logic_quizz.data.common.model

import yaroslavgorbach.logic_quizz.R

enum class PuzzleName(val resId: Int) {
    SNACK_TIME(R.string.snack_time_name),
    MATES_PLUS_DATES(R.string.mates_plus_dates_name),
    MORE_PAINTERS(R.string.more_painters_name),
    KITTENS_AND_KIDS(R.string.kittens_and_kids_name),
    JAZZ_BANDS_SOLOS(R.string.jazz_band_solos_name),
}

fun PuzzleName.findNext(): PuzzleName? {
    val currentIndex = PuzzleName.values().indexOf(this)
    return PuzzleName.values().getOrNull(currentIndex + 1)
}


fun PuzzleName.isLast(): Boolean {
    return this == PuzzleName.values().last()
}
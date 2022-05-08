package yaroslavgorbach.logic_quizz.data.common.model

import yaroslavgorbach.logic_quizz.R

enum class PuzzleName(val resId: Int) {
    SNACK_TIME(R.string.snack_time_name),
    MATES_PLUS_DATES(R.string.mates_plus_dates_name)
}

fun PuzzleName.findNext(): PuzzleName? {
    val currentIndex = PuzzleName.values().indexOf(this)
    return PuzzleName.values().getOrNull(currentIndex + 1)
}
package yaroslavgorbach.logic_quizz.data.puzzles.model

import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

data class PuzzleItem(
    val name: PuzzleName,
    val description: String,
    val difficultLevel: DifficultLevel,
    val isAvailable: Boolean,
    val isCompleted: Boolean
) {
    val isEasyTittleVisible: Boolean = name == PuzzleName.SNACK_TIME
    val isMiddleTittleVisible: Boolean = name == PuzzleName.MORE_PAINTERS
    val isHardTittleVisible: Boolean = false

    val isEasyBudgetVisible: Boolean = name == PuzzleName.MATES_PLUS_DATES
    val isMiddleBudgetVisible: Boolean = false
    val isHardBudgetVisible: Boolean = false
}
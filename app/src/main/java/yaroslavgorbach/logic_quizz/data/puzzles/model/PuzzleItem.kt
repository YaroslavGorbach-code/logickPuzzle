package yaroslavgorbach.logic_quizz.data.puzzles.model

import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

data class PuzzleItem(
    val name: PuzzleName,
    val description: String,
    val difficultLevel: DifficultLevel,
    val isAvailable: Boolean,
    val isCompleted: Boolean
)

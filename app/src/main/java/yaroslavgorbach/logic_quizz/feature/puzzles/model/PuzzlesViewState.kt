package yaroslavgorbach.logic_quizz.feature.puzzles.model

import yaroslavgorbach.logic_quizz.data.puzzles.model.DifficultLevel
import yaroslavgorbach.logic_quizz.data.puzzles.model.PuzzleItem
import yaroslavgorbach.logic_quizz.utills.UiMessage

data class PuzzlesViewState(
    val puzzleItems: List<PuzzleItem>,

    val allEasyPuzzlesDone: Boolean = puzzleItems.filter {
        it.difficultLevel == DifficultLevel.ONE || it.difficultLevel == DifficultLevel.TWO
    }.any { it.isCompleted.not() }.not(),

    val allMiddlePuzzlesDone: Boolean = puzzleItems.filter {
        it.difficultLevel == DifficultLevel.THREE || it.difficultLevel == DifficultLevel.FOUR
    }.any { it.isCompleted.not() }.not(),

    val allHardPuzzlesDone: Boolean = puzzleItems.filter {
        it.difficultLevel == DifficultLevel.FIVE || it.difficultLevel == DifficultLevel.SIX
    }.any { it.isCompleted.not() }.not(),

    val message: UiMessage<PuzzlesUiMessage>?,
) {
    companion object {
        val Test = PuzzlesViewState(
            puzzleItems = ArrayList(),
            message = null,
        )
    }
}

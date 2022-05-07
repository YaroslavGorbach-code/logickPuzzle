package yaroslavgorbach.logic_quizz.feature.puzzles.model

import yaroslavgorbach.logic_quizz.data.puzzles.model.PuzzleItem
import yaroslavgorbach.logic_quizz.utills.UiMessage

data class PuzzlesViewState(
    val puzzleItems: List<PuzzleItem>,
    val message: UiMessage<PuzzlesUiMessage>?,
) {
    companion object {
        val Test = PuzzlesViewState(
            puzzleItems = ArrayList(),
            message = null,
        )
    }
}
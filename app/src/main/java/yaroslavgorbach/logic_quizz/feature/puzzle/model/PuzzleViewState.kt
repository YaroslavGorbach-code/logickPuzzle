package yaroslavgorbach.logic_quizz.feature.puzzle.model

import yaroslavgorbach.logic_quizz.data.puzzle.model.Puzzle
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesUiMessage
import yaroslavgorbach.logic_quizz.utills.UiMessage

data class PuzzleViewState(
    val puzzle: Puzzle?,
    val message: UiMessage<PuzzleUiMessage>?,
    val hintedTitles: Pair<String, String>? = null,
    val isCheckAnswerVisible: Boolean = false,
) {
    companion object {
        val Test = PuzzleViewState(
            message = null,
            puzzle = null
        )
    }
}

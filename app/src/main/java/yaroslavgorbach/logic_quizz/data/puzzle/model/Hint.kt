package yaroslavgorbach.logic_quizz.data.puzzle.model

data class Hint(val index: Int, val text: String, val isVisible: Boolean = index == 0)

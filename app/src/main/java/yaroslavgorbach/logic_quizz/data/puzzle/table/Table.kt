package yaroslavgorbach.logic_quizz.data.puzzle.table

data class Table(
    val width: Int,
    val height: Int,
    val size: Int = width * height,
    val titleHorizontal: TableTitle,
    val titleVertical: TableTitle,
    val indexInPuzzleHorizontal: Int,
    val indexInPuzzleVertical: Int,
    val verticalTitleVisible: Boolean = indexInPuzzleVertical == 0,
    val horizontalTitleVisible: Boolean = indexInPuzzleHorizontal == 0
) {
    companion object {
        val Test = Table(
            4,
            4,
            titleHorizontal = TableTitle.Test,
            titleVertical = TableTitle.Test,
            indexInPuzzleHorizontal = 0,
            indexInPuzzleVertical = 0,
        )
    }

    val cells: MutableList<Cell> = ArrayList()

    init {
        repeat(size) {
            cells.add(Cell())
        }
    }

    data class Cell(val state: State = State.EMPTY) {
        enum class State {
            EMPTY, CORRECT, INCORRECT
        }

        fun reduceState(state: State): State {
            when (state) {
                State.EMPTY -> TODO()
                State.CORRECT -> TODO()
                State.INCORRECT -> TODO()
            }
        }
    }
}


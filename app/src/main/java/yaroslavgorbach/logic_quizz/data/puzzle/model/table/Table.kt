package yaroslavgorbach.logic_quizz.data.puzzle.model.table

data class Table(
    val width: Int,
    val height: Int,
    val size: Int = width * height,
    val titleHorizontal: TableTitle,
    val titleVertical: TableTitle,
    val indexInPuzzleHorizontal: Int,
    val indexInPuzzleVertical: Int,
    val verticalTitleVisible: Boolean = indexInPuzzleVertical == 0,
    val horizontalTitleVisible: Boolean = indexInPuzzleHorizontal == 0,
    var cells: MutableList<Cell> = List(size) {
        var indexHorizontal: Int = it
        var indexVertical: Int = 0

        while (indexHorizontal >= titleHorizontal.items.size) {
            indexHorizontal -= titleHorizontal.items.size
            indexVertical += 1
        }

        Cell(
            index = it,
            titleHorizontal = titleHorizontal.items[indexHorizontal],
            titleVertical = titleVertical.items[indexVertical]
        )

    }.toMutableList()
) {
    data class Cell(
        val index: Int,
        val titleHorizontal: String,
        val titleVertical: String,
        val state: State = State.EMPTY,
        val filledAutomatically: Boolean = false,
        val filledAutomaticallyByIndex: Int = 0,
    ) {
        enum class State {
            EMPTY, CORRECT, INCORRECT
        }

        fun reduceState(): State {
            return when (state) {
                State.EMPTY -> State.INCORRECT
                State.CORRECT -> State.EMPTY
                State.INCORRECT -> State.CORRECT
            }
        }
    }

    fun getCheckedPairs(): List<Pair<String, String>> {
        return cells.filter { it.state == Cell.State.CORRECT }.map {
            Pair(it.titleHorizontal, it.titleVertical)
        }
    }
}


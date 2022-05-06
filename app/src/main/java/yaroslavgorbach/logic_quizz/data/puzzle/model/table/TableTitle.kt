package yaroslavgorbach.logic_quizz.data.puzzle.model.table

data class TableTitle(
    val name: String,
    val items: List<String>,
    val orderNumber: Int,
    val orientation: Orientation,
    val isMain: Boolean = orderNumber in 1..2
){
    companion object{
        val Test = TableTitle("", listOf("1", "2"), 0, Orientation.HORIZONTAL)
    }

    enum class Orientation{
        HORIZONTAL, VERTICAL
    }
}

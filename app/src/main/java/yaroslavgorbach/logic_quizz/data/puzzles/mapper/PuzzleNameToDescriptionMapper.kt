package yaroslavgorbach.logic_quizz.data.puzzles.mapper

import android.content.Context
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

class PuzzleNameToDescriptionMapper(val context: Context) {

    fun map(name: PuzzleName): String {
        return when (name) {
            PuzzleName.SNACK_TIME -> "Test"
        }
    }
}
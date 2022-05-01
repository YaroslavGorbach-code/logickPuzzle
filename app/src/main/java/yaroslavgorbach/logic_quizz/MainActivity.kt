package yaroslavgorbach.logic_quizz

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import yaroslavgorbach.logic_quizz.data.puzzle.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzle.factory.PuzzleFactory
import yaroslavgorbach.logic_quizz.feature.common.ui.theme.LogicPuzzleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LogicPuzzleTheme {
                Log.i(
                    "dsdcds", PuzzleFactory(application).create(PuzzleName.SNACK_TIME).toString()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LogicPuzzleTheme {

    }
}
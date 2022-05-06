package yaroslavgorbach.logic_quizz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.logic_quizz.feature.common.ui.theme.LogicPuzzleTheme
import yaroslavgorbach.logic_quizz.feature.puzzles.ui.PuzzlesUi

@AndroidEntryPoint
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@InternalCoroutinesApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            LogicPuzzleTheme {
                AppNavigation(navController = navController)
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
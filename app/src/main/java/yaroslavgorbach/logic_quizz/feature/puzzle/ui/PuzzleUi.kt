package yaroslavgorbach.logic_quizz.feature.puzzle.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleAction
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleViewState
import yaroslavgorbach.logic_quizz.feature.puzzle.presentation.PuzzleViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun PuzzleUi(onBack: () -> Unit) {
    PuzzleUi(viewModel = hiltViewModel(), onBack = onBack)
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun PuzzleUi(
    viewModel: PuzzleViewModel,
    onBack: () -> Unit
) {
    val viewState = viewModel.state.collectAsState()

    PuzzleUi(
        state = viewState.value,
        actioner = viewModel::submitAction,
        onBack = onBack,
        clearMessage = viewModel::clearMessage
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun PuzzleUi(
    state: PuzzleViewState,
    actioner: (PuzzleAction) -> Unit,
    onBack: () -> Unit,
    clearMessage: (id: Long) -> Unit
) {

    state.message?.let { message ->
        when (message.message) {

            else -> {
            }
        }
    }


}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecordingsPreview() {
    PuzzleUi {}
}
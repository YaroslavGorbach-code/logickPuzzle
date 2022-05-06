package yaroslavgorbach.logic_quizz.feature.puzzles.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import yaroslavgorbach.logic_quizz.R
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.feature.common.ui.theme.getOnBackgroundColor
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesAction
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesViewState
import yaroslavgorbach.logic_quizz.feature.puzzles.presentation.PuzzlesViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun PuzzlesUi(navigateToPuzzle: (name: PuzzleName) -> Unit) {
    PuzzlesUi(viewModel = hiltViewModel(), navigateToPuzzle = navigateToPuzzle)
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun PuzzlesUi(
    viewModel: PuzzlesViewModel,
    navigateToPuzzle: (name: PuzzleName) -> Unit,
) {
    val viewState = viewModel.state.collectAsState()

    PuzzlesUi(
        state = viewState.value,
        actioner = viewModel::submitAction,
        navigateToPuzzle = navigateToPuzzle,
        clearMessage = viewModel::clearMessage
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun PuzzlesUi(
    state: PuzzlesViewState,
    actioner: (PuzzlesAction) -> Unit,
    navigateToPuzzle: (name: PuzzleName) -> Unit,
    clearMessage: (id: Long) -> Unit
) {

    state.message?.let { message ->
        when (message.message) {
            else -> {
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.caption,
                fontSize = 32.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.app_description),
                modifier = Modifier.padding(bottom = 32.dp, start = 8.dp, end = 8.dp),
            )


            LazyColumn {
                items(state.puzzleItems) { item ->
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(color = getOnBackgroundColor())
                    )
                    PuzzleItemUi(item = item) {
                        navigateToPuzzle(item.name)
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(color = getOnBackgroundColor())
                    )
                }
            }
        }
    }

}

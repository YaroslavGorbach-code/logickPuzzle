package yaroslavgorbach.logic_quizz.feature.puzzle.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            state.puzzle?.let { puzzle ->
                Log.i("dsdads", puzzle.titles.toString())
                Column(modifier = Modifier.align(Alignment.Center)) {
                    repeat(puzzle.numberOfRows) { rowNumber ->
                        Spacer(modifier = Modifier.size(1.dp))

                        Row {
                            puzzle.tables.forEach { table ->
                                if (table.indexInPuzzleVertical == rowNumber) {
                                    TableUi(
                                        table = table,
                                        modifier = Modifier.size(90.dp)
                                    ) { table, cell ->
                                        actioner(PuzzleAction.OnCell(table, cell))
                                    }

                                    Spacer(modifier = Modifier.size(1.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PuzzlePreview() {
    PuzzleUi {}
}
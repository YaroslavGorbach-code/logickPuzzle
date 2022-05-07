package yaroslavgorbach.logic_quizz.feature.puzzle.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import yaroslavgorbach.logic_quizz.R
import yaroslavgorbach.logic_quizz.feature.common.ui.theme.getOnBackgroundColor
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleAction
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleViewState
import yaroslavgorbach.logic_quizz.feature.puzzle.presentation.PuzzleViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun PuzzleUi(onBack: () -> Unit) {
   PuzzleUi(
        viewModel = hiltViewModel(),
        onBack = onBack
    )
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
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 8.dp)
                        .align(CenterVertically)
                        .clickable {
                            onBack()
                        }
                )

                Text(
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(start = 16.dp),
                    text = stringResource(
                        id =
                        state.puzzle?.name?.resId ?: R.string.app_name
                    ),
                    style = MaterialTheme.typography.caption
                )
            }

            state.puzzle?.let { puzzle ->
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(0.55f)
                        .padding(top = 32.dp)
                ) {
                    repeat(puzzle.numberOfRows) { rowNumber ->
                        Spacer(modifier = Modifier.size(1.dp))

                        Row {
                            puzzle.tables.forEach { table ->
                                if (table.indexInPuzzleVertical == rowNumber) {
                                    TableUi(
                                        table = table,
                                        modifier = Modifier.size(90.dp),
                                        hintedTitles = state.hintedTitles
                                    ) { table, cell ->
                                        if (cell.filledAutomatically.not()) {
                                            actioner(PuzzleAction.OnCell(table, cell))
                                        }
                                    }

                                    Spacer(modifier = Modifier.size(1.dp))
                                }
                            }
                        }
                    }
                    actioner(PuzzleAction.TableUpdated)
                }
            }

            Column(
                modifier = Modifier
                    .weight(0.45f)
            ) {

                if (state.isCheckAnswerVisible) {
                    OutlinedButton(
                        onClick = { actioner(PuzzleAction.CheckAnswer) },
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(bottom = 8.dp),
                        shape = RoundedCornerShape(20),
                        border = BorderStroke(1.dp, color = Color.Green)
                    ) {
                        Text(text = stringResource(id = R.string.check_answer), color = Color.Green)
                    }
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(color = getOnBackgroundColor())
                )

                Text(
                    text = stringResource(id = R.string.clues),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.caption
                )

                LazyColumn {
                    items(state.puzzle?.clues ?: emptyList()) { clue ->
                        Text(
                            text = clue,
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp))
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
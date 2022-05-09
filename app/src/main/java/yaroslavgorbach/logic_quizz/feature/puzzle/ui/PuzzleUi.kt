package yaroslavgorbach.logic_quizz.feature.puzzle.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import yaroslavgorbach.logic_quizz.R
import yaroslavgorbach.logic_quizz.feature.common.ui.theme.getOnBackgroundColor
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleAction
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleUiMessage
import yaroslavgorbach.logic_quizz.feature.puzzle.model.PuzzleViewState
import yaroslavgorbach.logic_quizz.feature.puzzle.presentation.PuzzleViewModel
import yaroslavgorbach.logic_quizz.utills.UiMessage

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
            PuzzleUiMessage.ShowPuzzleErrorDialog -> {
                ShowFailDialog(clearMessage, message)
            }
            PuzzleUiMessage.ShowWinDialog -> {
                ShowWinDialog(clearMessage, message, onBack)
            }
            PuzzleUiMessage.ShowStoryDialog -> {
                ShowStoryDialog(state, clearMessage, message)
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
                LazyColumn(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .weight(0.70f)
                        .padding(top = 32.dp)
                ) {
                    items(puzzle.numberOfRows) { rowNumber ->

                        Spacer(modifier = Modifier.size(1.dp))

                        LazyRow() {
                            items(puzzle.tables) { table ->
                                if (table.indexInPuzzleVertical == rowNumber) {
                                    TableUi(
                                        cellSize = table.uiCellSize,
                                        table = table,
                                        modifier = Modifier.size(table.uiTableSize.dp),
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
                    .weight(0.30f)
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
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)
                        )
                    }
                }
            }

        }
    }
}

@Composable
private fun ShowWinDialog(
    clearMessage: (id: Long) -> Unit,
    message: UiMessage<PuzzleUiMessage>,
    onBack: () -> Unit
) {
    AlertDialog(onDismissRequest = {
        clearMessage(message.id)
        onBack()
    }, buttons = {}, title = {

        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(Center),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_correct),
                contentDescription = ""
            )
        }

    }, text = {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(id = R.string.congratulations),
                style = MaterialTheme.typography.caption,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(id = R.string.you_completed_puzzle),
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    })
}

@Composable
private fun ShowFailDialog(
    clearMessage: (id: Long) -> Unit,
    message: UiMessage<PuzzleUiMessage>
) {
    AlertDialog(onDismissRequest = {
        clearMessage(message.id)
    }, buttons = {}, title = {

        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(Center),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_wrong),
                contentDescription = ""
            )
        }

    }, text = {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(id = R.string.oops),
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
                fontSize = 34.sp
            )

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(id = R.string.check_answers),
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    })
}

@Composable
private fun ShowStoryDialog(
    state: PuzzleViewState,
    clearMessage: (id: Long) -> Unit,
    message: UiMessage<PuzzleUiMessage>
) {
    AlertDialog(onDismissRequest = {
        clearMessage(message.id)
    }, buttons = {

    }, title = {

        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(Center),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_book),
                contentDescription = ""
            )
        }

    }, text = {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(
                    id = state.puzzle?.name?.resId ?: R.string.app_name
                ),
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = state.puzzle?.story ?: "",
                fontSize = 16.sp,
                textAlign = TextAlign.Justify
            )
        }
    })
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PuzzlePreview() {
    PuzzleUi {}
}
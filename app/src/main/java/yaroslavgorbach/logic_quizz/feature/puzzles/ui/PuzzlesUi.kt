package yaroslavgorbach.logic_quizz.feature.puzzles.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import yaroslavgorbach.logic_quizz.R
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.common.model.isLast
import yaroslavgorbach.logic_quizz.feature.common.ui.theme.getOnBackgroundColor
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesAction
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesUiMessage
import yaroslavgorbach.logic_quizz.feature.puzzles.model.PuzzlesViewState
import yaroslavgorbach.logic_quizz.feature.puzzles.presentation.PuzzlesViewModel
import yaroslavgorbach.logic_quizz.utills.UiMessage
import yaroslavgorbach.logic_quizz.utills.findActivity

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
    clearMessage: (id: Long) -> Unit,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                actioner(PuzzlesAction.LoadPuzzle)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    state.message?.let { message ->
        when (message.message) {
            is PuzzlesUiMessage.ShowPuzzleUnAvailableDialog -> ShowUnavailableDialog(
                message.message.name,
                actioner,
                clearMessage,
                message
            )
            is PuzzlesUiMessage.ShowRewardAd -> {
                actioner(
                    PuzzlesAction.ShowRewordAd(
                        activity = requireNotNull(LocalContext.current.findActivity()),
                        puzzleName = message.message.puzzleName
                    )
                )
                clearMessage(message.id)
            }
            PuzzlesUiMessage.ShowHelpDialog -> {
                ShowHelpDialogDialog(clearMessage = clearMessage, message = message, navigateToPuzzle = navigateToPuzzle)
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Row() {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.caption,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f)
                )

                Icon(
                    Icons.Default.HelpOutline,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                        .clickable {
                            actioner(PuzzlesAction.ShowHelpDialog)
                        }
                )
            }

            Text(
                text = stringResource(id = R.string.app_description),
                modifier = Modifier.padding(bottom = 32.dp, start = 8.dp, end = 8.dp),
            )

            LazyColumn {
                items(state.puzzleItems) { item ->

                    if (item.isEasyTittleVisible) {
                        Text(
                            text = stringResource(id = R.string.easy),
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.caption,
                            fontSize = 16.sp
                        )
                    }

                    if (item.isMiddleTittleVisible) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(color = getOnBackgroundColor())
                        )

                        ShowEasyAchievement(state)

                        Text(
                            text = stringResource(id = R.string.middle),
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.caption,
                            fontSize = 16.sp
                        )
                    }

                    if (item.isHardTittleVisible) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(color = getOnBackgroundColor())
                        )

                        ShowMiddleAchievement(state)

                        Text(
                            text = stringResource(id = R.string.hard),
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.caption,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(color = getOnBackgroundColor())
                    )

                    PuzzleItemUi(item = item, onPuzzle = {
                        navigateToPuzzle(item.name)
                    }, showUnavailableDialog = {
                        actioner(PuzzlesAction.ShowPuzzleUnAvailableDialog(item.name))
                    })

                    if (item.name.isLast()){
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(color = getOnBackgroundColor())
                        )

                        ShowHardAchievement(state)
                    }
                }

            }


        }

    }
}

@Composable
private fun ShowEasyAchievement(state: PuzzlesViewState) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 40.dp)) {

        if (state.allEasyPuzzlesDone.not()) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(CenterHorizontally)
                    .padding(bottom = 8.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_brain_blured),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(
                    id =
                    R.string.all_easy_puzzles_explanation
                ).uppercase(),
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
            )
        } else {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(CenterHorizontally)
                    .padding(bottom = 8.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_brain_easy),
                contentDescription = ""
            )

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(
                    id =
                    R.string.congratulations
                ).uppercase(),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption,
            )

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(
                    id =
                    R.string.all_easy_puzzles_solved
                ).uppercase(),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
            )
        }

    }
}

@Composable
private fun ShowMiddleAchievement(state: PuzzlesViewState) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 40.dp)) {

        if (state.allMiddlePuzzlesDone.not()) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(CenterHorizontally)
                    .padding(bottom = 8.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_brain_blured),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(
                    id =
                    R.string.all_middle_puzzles_explanation
                ).uppercase(),
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
            )
        } else {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(CenterHorizontally)
                    .padding(bottom = 8.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_brain_middle),
                contentDescription = ""
            )

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(
                    id =
                    R.string.congratulations
                ).uppercase(),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption,
            )

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(
                    id =
                    R.string.all_middle_puzzles_solved
                ).uppercase(),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
            )
        }

    }
}

@Composable
private fun ShowHardAchievement(state: PuzzlesViewState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp)
    ) {

        if (state.allHardPuzzlesDone.not()) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(CenterHorizontally)
                    .padding(bottom = 8.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_brain_blured),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(
                    id =
                    R.string.all_hard_puzzles_explanation
                ).uppercase(),
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
            )
        } else {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(CenterHorizontally)
                    .padding(bottom = 8.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_brain_hard),
                contentDescription = ""
            )

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(
                    id =
                    R.string.congratulations
                ).uppercase(),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption,
            )

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(
                    id =
                    R.string.all_hard_puzzles_solved
                ).uppercase(),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
            )
        }

    }
}

@Composable
private fun ShowUnavailableDialog(
    name: PuzzleName,
    actioner: (PuzzlesAction) -> Unit,
    clearMessage: (id: Long) -> Unit,
    message: UiMessage<PuzzlesUiMessage>
) {
    AlertDialog(onDismissRequest = {
        clearMessage(message.id)
    }, buttons = {
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    actioner(
                        PuzzlesAction.RequestShowRewordAd(
                            name
                        )
                    )
                    clearMessage(message.id)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(CenterHorizontally),
            ) {
                Text(text = stringResource(id = R.string.view_ads), color = Color.White)
            }
        }

    }, title = {

        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_lock),
                contentDescription = ""
            )
        }

    }, text = {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(id = R.string.puzzle_is_unavailable),
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(id = R.string.puzzle_is_unavailable_explanation),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    })
}

@Composable
private fun ShowHelpDialogDialog(
    navigateToPuzzle: (name: PuzzleName) -> Unit,
    clearMessage: (id: Long) -> Unit,
    message: UiMessage<PuzzlesUiMessage>
) {
    val scroll = rememberScrollState(0)

    AlertDialog(onDismissRequest = {
        clearMessage(message.id)
    }, buttons = {
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    navigateToPuzzle(PuzzleName.TRAINING_PUZZLE)
                    clearMessage(message.id)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(CenterHorizontally),
            ) {
                Text(text = stringResource(id = R.string.play_the_simple_button).uppercase(), color = Color.White)
            }
        }

    }, title = {
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scroll)) {
            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = stringResource(id = R.string.how_to_play),
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier.align(CenterHorizontally).padding(top = 8.dp),
                text = stringResource(id = R.string.how_to_play_explanation),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify
            )

            Text(
                modifier = Modifier.align(CenterHorizontally).padding(top = 8.dp),
                text = stringResource(id = R.string.play_the_basics),
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier.align(CenterHorizontally).padding(top = 8.dp),
                text = stringResource(id = R.string.play_the_basics_explanation),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify
            )
        }
    })
}
package yaroslavgorbach.logic_quizz.feature.puzzles.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yaroslavgorbach.logic_quizz.R
import yaroslavgorbach.logic_quizz.data.puzzles.model.DifficultLevel
import yaroslavgorbach.logic_quizz.data.puzzles.model.PuzzleItem
import yaroslavgorbach.logic_quizz.feature.common.ui.theme.LightBlue
import yaroslavgorbach.logic_quizz.feature.common.ui.theme.getOnBackgroundHinted

@Composable
fun PuzzleItemUi(item: PuzzleItem, onPuzzle: () -> Unit, showUnavailableDialog: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                if (item.isAvailable) {
                    onPuzzle()
                } else {
                    showUnavailableDialog()
                }
            }
    ) {
        Column(
            modifier = Modifier
                .weight(0.4f)
                .padding(start = 8.dp)
        ) {

            Row {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 16.dp),
                    text = stringResource(id = item.name.resId),
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(modifier = Modifier
                    .padding(top = 8.dp, end = 16.dp)) {
                    DifficultLevel.values().filterNot { it == DifficultLevel.TRAIN }.forEach { difficultLevel ->
                        Icon(
                            Icons.Default.Psychology,
                            contentDescription = "",
                            tint = if (difficultLevel.ordinal <= item.difficultLevel.ordinal) LightBlue else getOnBackgroundHinted(),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.weight(0.6f)
        ) {

            Text(
                text = item.description,
                maxLines = 2,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, top = 4.dp),
            )

            if (item.isCompleted) {
                Image(
                    ImageVector.vectorResource(id = R.drawable.ic_done),
                    modifier = Modifier
                        .align(CenterVertically)
                        .size(60.dp)
                        .padding(bottom = 32.dp),
                    contentDescription = ""
                )
            } else {
                if (item.isAvailable) {
                    Icon(
                        Icons.Default.NavigateNext,
                        modifier = Modifier
                            .align(CenterVertically)
                            .size(70.dp)
                            .padding(bottom = 32.dp),
                        contentDescription = ""
                    )
                } else {
                    Icon(
                        Icons.Default.Lock,
                        modifier = Modifier
                            .align(CenterVertically)
                            .size(55.dp)
                            .padding(bottom = 32.dp),
                        contentDescription = ""
                    )
                }
            }

        }
    }
}

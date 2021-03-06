package yaroslavgorbach.logic_quizz.feature.puzzle.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import yaroslavgorbach.logic_quizz.data.puzzle.model.table.Table
import yaroslavgorbach.logic_quizz.feature.common.ui.theme.getOnBackgroundHinted
import yaroslavgorbach.logic_quizz.feature.common.ui.theme.getOnBackgroundColor

@ExperimentalFoundationApi
@Composable
fun TableUi(
    modifier: Modifier = Modifier,
    cellSize: Int = 30,
    table: Table,
    hintedTitles: Pair<String, String>?,
    onCell: (table: Table, cell: Table.Cell) -> Unit
) {

    Column {
        if (table.verticalTitleVisible) {
            Row(Modifier.align(End), verticalAlignment = Alignment.CenterVertically) {
                repeat(table.titleHorizontal.items.size) { index ->
                    Box(
                        modifier = Modifier
                            .vertical()
                            .width(100.dp)
                            .height(cellSize.dp)
                            .rotate(-90f)
                            .padding(1.dp)
                            .background(color = if (hintedTitles?.first == table.titleHorizontal.items[index]) getOnBackgroundHinted() else getOnBackgroundColor())
                    ) {
                        Text(
                            text = table.titleHorizontal.items[index],
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .align(CenterStart)
                        )
                    }
                }
            }
        }
        Row {
            if (table.horizontalTitleVisible){
                Column {
                    repeat(table.titleVertical.items.size) { index ->
                        Box(
                            modifier = Modifier
                                .height(cellSize.dp)
                                .width(100.dp)
                                .padding(1.dp)
                                .background(color = if (hintedTitles?.second == table.titleVertical.items[index]) getOnBackgroundHinted() else getOnBackgroundColor())
                        ) {
                            Text(
                                text = table.titleVertical.items[index],
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .align(CenterStart)
                            )
                        }
                    }
                }
            }

            LazyVerticalGrid(
                modifier = modifier,
                cells = GridCells.Fixed(table.width),
                content = {
                    items(table.cells) { cell ->
                        Box(
                            modifier = Modifier
                                .size(cellSize.dp)
                                .padding(1.dp)
                                .background(color = getOnBackgroundColor())
                                .clickable { onCell(table, cell) }
                        ) {
                            when (cell.state) {
                                Table.Cell.State.EMPTY -> {
                                }
                                Table.Cell.State.CORRECT -> {
                                    Icon(
                                        Icons.Default.Check,
                                        null,
                                        tint = Color.Green,
                                        modifier = Modifier.align(Center)
                                    )
                                }
                                Table.Cell.State.INCORRECT -> {
                                    Icon(
                                        Icons.Default.Close,
                                        null,
                                        tint = Color.Red,
                                        modifier = Modifier.align(Center)
                                    )
                                }
                            }
                        }
                    }
                })
        }
    }
}

fun Modifier.vertical() =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2)
            )
        }
    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TablePreview() {

}
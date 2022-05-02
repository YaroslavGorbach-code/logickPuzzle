package yaroslavgorbach.logic_quizz.feature.puzzle.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import yaroslavgorbach.logic_quizz.data.puzzle.table.Table

@ExperimentalFoundationApi
@Composable
fun TableUi(modifier: Modifier = Modifier, table: Table) {

    Column {
        if (table.verticalTitleVisible){
            Row(Modifier.align(End)) {
                repeat(table.titleHorizontal.items.size) {
                    Box(
                        modifier = Modifier
                            .width(30.dp)
                            .height(100.dp)
                            .padding(1.dp)
                            .background(color = Color.Green)
                    ) {

                    }
                }
            }
        }

        Row {
            if (table.horizontalTitleVisible){
                Column {
                    repeat(table.titleVertical.items.size) {
                        Box(
                            modifier = Modifier
                                .height(30.dp)
                                .width(100.dp)
                                .padding(1.dp)
                                .background(color = Color.Green)
                        ) {

                        }
                    }
                }
            }

            LazyVerticalGrid(
                modifier = modifier,
                cells = GridCells.Fixed(table.width),
                content = {
                    items(table.cells) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .padding(1.dp)
                                .background(color = Color.Red)
                        ) {

                        }
                    }
                })
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TablePreview() {

}
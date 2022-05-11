package yaroslavgorbach.logic_quizz.data.puzzle.factory

import android.content.Context
import yaroslavgorbach.logic_quizz.R
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzle.model.table.TableTitle

class PuzzleTitlesFactory(private val context: Context) {

    fun create(puzzleName: PuzzleName): List<TableTitle> {
        when (puzzleName) {
            PuzzleName.SNACK_TIME -> {
                return listOf(
                    TableTitle(
                        name = context.getString(R.string.snack_time_items_1_name),
                        items = context.resources.getStringArray(R.array.snack_time_items_1_friends)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.snack_time_items_2_food),
                        items = context.resources.getStringArray(R.array.snack_time_items_2_food)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.snack_time_items_3_drinks),
                        items = context.resources.getStringArray(R.array.snack_time_items_3_drinks)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.snack_time_items_2_food),
                        items = context.resources.getStringArray(R.array.snack_time_items_2_food)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    )
                )
            }
            PuzzleName.MATES_PLUS_DATES -> {
                return listOf(
                    TableTitle(
                        name = context.getString(R.string.mates_plus_dates_items_1_name),
                        items = context.resources.getStringArray(R.array.mates_plus_dates_items_1_friends)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.mates_plus_dates_items_2_name),
                        items = context.resources.getStringArray(R.array.mates_plus_dates_items_2_dates)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.mates_plus_dates_items_3_name),
                        items = context.resources.getStringArray(R.array.mates_plus_dates_items_3_places)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.mates_plus_dates_items_2_name),
                        items = context.resources.getStringArray(R.array.mates_plus_dates_items_2_dates)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    )
                )
            }
            PuzzleName.MORE_PAINTERS -> {
                return listOf(
                    TableTitle(
                        name = context.getString(R.string.more_painters_items_1_name),
                        items = context.resources.getStringArray(R.array.more_painters_items_1_painters)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.more_painters_items_2_name),
                        items = context.resources.getStringArray(R.array.more_painters_items_2_houses)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.more_painters_items_3_name),
                        items = context.resources.getStringArray(R.array.more_painters_items_3_colors)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.more_painters_items_2_name),
                        items = context.resources.getStringArray(R.array.more_painters_items_2_houses)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    )
                )
            }
            PuzzleName.KITTENS_AND_KIDS -> {
                return listOf(
                    TableTitle(
                        name = context.getString(R.string.kittens_and_kids_items_1_name),
                        items = context.resources.getStringArray(R.array.kittens_and_kids_items_1_kids)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.kittens_and_kids_items_3_name),
                        items = context.resources.getStringArray(R.array.kittens_and_kids_items_3_things)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.kittens_and_kids_items_2_name),
                        items = context.resources.getStringArray(R.array.kittens_and_kids_items_2_weight)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.kittens_and_kids_items_3_name),
                        items = context.resources.getStringArray(R.array.kittens_and_kids_items_3_things)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    )
                )
            }
            PuzzleName.JAZZ_BANDS_SOLOS -> {
                return listOf(
                    TableTitle(
                        name = context.getString(R.string.jazz_band_solos_1_name),
                        items = context.resources.getStringArray(R.array.jazz_band_solos_1_first_names)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.jazz_band_solos_items_3_name),
                        items = context.resources.getStringArray(R.array.jazz_band_solos_items_3_instruments)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.jazz_band_solos_2_name),
                        items = context.resources.getStringArray(R.array.jazz_band_solos_items_2_last_names)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.jazz_band_solos_items_3_name),
                        items = context.resources.getStringArray(R.array.jazz_band_solos_items_3_instruments)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    )
                )
            }
            PuzzleName.TRAINING_PUZZLE -> {
                return listOf(
                    TableTitle(
                        name = context.getString(R.string.training_puzzle_items_1_name),
                        items = context.resources.getStringArray(R.array.training_puzzle_items_1_names)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.training_puzzle_items_3_name),
                        items = context.resources.getStringArray(R.array.training_puzzle_items_3_fruits)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.training_puzzle_items_2_name),
                        items = context.resources.getStringArray(R.array.training_puzzle_items_2_age)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.training_puzzle_items_3_name),
                        items = context.resources.getStringArray(R.array.training_puzzle_items_3_fruits)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    )
                )
            }
            PuzzleName.MULTICOLOUR_DOORS -> {
                return listOf(
                    TableTitle(
                        name = context.getString(R.string.multicolour_doors_items_1_friends_name),
                        items = context.resources.getStringArray(R.array.multicolour_doors_items_1_friends)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.multicolour_doors_items_3_streets_name),
                        items = context.resources.getStringArray(R.array.multicolour_doors_items_3_streets)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.VERTICAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.multicolour_doors_items_2_colours_name),
                        items = context.resources.getStringArray(R.array.multicolour_doors_items_2_colours)
                            .toList(),
                        orderNumber = 1,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    ),
                    TableTitle(
                        name = context.getString(R.string.multicolour_doors_items_3_streets_name),
                        items = context.resources.getStringArray(R.array.multicolour_doors_items_3_streets)
                            .toList(),
                        orderNumber = 2,
                        orientation = TableTitle.Orientation.HORIZONTAL
                    )
                )
            }
        }
    }
}
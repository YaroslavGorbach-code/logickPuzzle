package yaroslavgorbach.logic_quizz.data.puzzle.factory

import android.content.Context
import yaroslavgorbach.logic_quizz.R
import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName

class CorrectPairsProvider(private val context: Context) {

    fun provide(puzzleName: PuzzleName): List<Pair<String, String>> {
        return when (puzzleName) {
            PuzzleName.FAMILY_TRIPS -> {
                val years =
                    context.resources.getStringArray(R.array.family_trips_items_1_years)
                        .toList()
                val children =
                    context.resources.getStringArray(R.array.family_trips_items_2_children)
                        .toList()
                val locations =
                    context.resources.getStringArray(R.array.family_trips_items_3_locations)
                        .toList()

                listOf<Pair<String, String>>(
                    Pair(years[0], children[0]),
                    Pair(years[0], locations[0]),
                    Pair(years[1], children[3]),
                    Pair(years[1], locations[1]),
                    Pair(years[2], children[2]),
                    Pair(years[2], locations[2]),
                    Pair(years[3], children[1]),
                    Pair(years[3], locations[3]),
                )
            }
            PuzzleName.JUST_A_THOUGHT -> {
                val names =
                    context.resources.getStringArray(R.array.just_a_thought_items_1_names)
                        .toList()
                val subjects =
                    context.resources.getStringArray(R.array.just_a_thought_items_2_subject)
                        .toList()
                val numbers =
                    context.resources.getStringArray(R.array.just_a_thought_items_3_numbers)
                        .toList()

                listOf<Pair<String, String>>(
                    Pair(names[0], subjects[1]),
                    Pair(names[0], numbers[0]),
                    Pair(names[1], subjects[2]),
                    Pair(names[1], numbers[2]),
                    Pair(names[2], subjects[0]),
                    Pair(names[2], numbers[1]),
                )
            }
            PuzzleName.DRACONIA_TRAINERS -> {
                val names =
                    context.resources.getStringArray(R.array.draconia_trainers_items_1_trainers)
                        .toList()
                val dragons =
                    context.resources.getStringArray(R.array.draconia_trainers_items_2_dragons)
                        .toList()
                val colors =
                    context.resources.getStringArray(R.array.draconia_trainers_items_3_colors)
                        .toList()

                listOf<Pair<String, String>>(
                    Pair(names[0], colors[0]),
                    Pair(names[0], dragons[2]),
                    Pair(names[1], colors[1]),
                    Pair(names[1], dragons[0]),
                    Pair(names[2], colors[2]),
                    Pair(names[2], dragons[3]),
                    Pair(names[3], colors[3]),
                    Pair(names[3], dragons[1]),
                )
            }

            PuzzleName.WHO_ATE_WHICH_FRUIT -> {
                val names =
                    context.resources.getStringArray(R.array.who_ate_which_fruit_items_1_friends)
                        .toList()
                val fruits =
                    context.resources.getStringArray(R.array.who_ate_which_fruit_items_2_fruits)
                        .toList()
                val days =
                    context.resources.getStringArray(R.array.who_ate_which_fruit_items_3_days)
                        .toList()

                listOf<Pair<String, String>>(
                    Pair(names[0], fruits[0]),
                    Pair(names[0], days[2]),
                    Pair(names[1], fruits[3]),
                    Pair(names[1], days[3]),
                    Pair(names[2], fruits[1]),
                    Pair(names[2], days[1]),
                    Pair(names[3], fruits[2]),
                    Pair(names[3], days[0]),
                )
            }

            PuzzleName.MULTICOLOUR_DOORS -> {
                val names =
                    context.resources.getStringArray(R.array.multicolour_doors_items_1_friends).toList()
                val colors =
                    context.resources.getStringArray(R.array.multicolour_doors_items_2_colours).toList()
                val streets =
                    context.resources.getStringArray(R.array.multicolour_doors_items_3_streets).toList()

                listOf<Pair<String, String>>(
                    Pair(names[0], colors[2]),
                    Pair(names[0], streets[2]),
                    Pair(names[1], colors[1]),
                    Pair(names[1], streets[0]),
                    Pair(names[2], colors[0]),
                    Pair(names[2], streets[1]),
                )
            }
            PuzzleName.TRAINING_PUZZLE -> {
                val names =
                    context.resources.getStringArray(R.array.training_puzzle_items_1_names).toList()
                val ages =
                    context.resources.getStringArray(R.array.training_puzzle_items_2_age).toList()
                val fruits =
                    context.resources.getStringArray(R.array.training_puzzle_items_3_fruits).toList()

                listOf<Pair<String, String>>(
                    Pair(names[0], ages[2]),
                    Pair(names[0], fruits[1]),
                    Pair(names[1], ages[0]),
                    Pair(names[1], fruits[0]),
                    Pair(names[2], ages[1]),
                    Pair(names[2], fruits[2]),
                )
            }
            PuzzleName.SNACK_TIME -> {
                val foods =
                    context.resources.getStringArray(R.array.snack_time_items_2_food).toList()
                val drinks =
                    context.resources.getStringArray(R.array.snack_time_items_3_drinks).toList()
                val people =
                    context.resources.getStringArray(R.array.snack_time_items_1_friends).toList()

                listOf<Pair<String, String>>(
                    Pair(people[0], drinks[0]),
                    Pair(people[0], foods[0]),
                    Pair(people[1], drinks[1]),
                    Pair(people[1], foods[2]),
                    Pair(people[2], drinks[2]),
                    Pair(people[2], foods[1]),
                )
            }
            PuzzleName.MATES_PLUS_DATES -> {
                val places =
                    context.resources.getStringArray(R.array.mates_plus_dates_items_3_places).toList()
                val dates =
                    context.resources.getStringArray(R.array.mates_plus_dates_items_2_dates).toList()
                val people =
                    context.resources.getStringArray(R.array.mates_plus_dates_items_1_friends).toList()

                listOf<Pair<String, String>>(
                    Pair(people[2], dates[1]),
                    Pair(people[2], places[0]),
                    Pair(people[0], dates[2]),
                    Pair(people[0], places[2]),
                    Pair(people[1], dates[0]),
                    Pair(people[1], places[1]),
                )
            }
            PuzzleName.MORE_PAINTERS -> {
                val painters =
                    context.resources.getStringArray(R.array.more_painters_items_1_painters).toList()
                val houses =
                    context.resources.getStringArray(R.array.more_painters_items_2_houses).toList()
                val colors =
                    context.resources.getStringArray(R.array.more_painters_items_3_colors).toList()

                listOf<Pair<String, String>>(
                    Pair(painters[0], houses[3]),
                    Pair(painters[0], colors[4]),
                    Pair(painters[1], houses[0]),
                    Pair(painters[1], colors[2]),
                    Pair(painters[2], houses[4]),
                    Pair(painters[2], colors[0]),
                    Pair(painters[3], houses[1]),
                    Pair(painters[3], colors[3]),
                    Pair(painters[4], houses[2]),
                    Pair(painters[4], colors[1]),
                )
            }
            PuzzleName.KITTENS_AND_KIDS -> {
                val kids =
                    context.resources.getStringArray(R.array.kittens_and_kids_items_1_kids).toList()
                val weight =
                    context.resources.getStringArray(R.array.kittens_and_kids_items_2_weight)
                        .toList()
                val things =
                    context.resources.getStringArray(R.array.kittens_and_kids_items_3_things)
                        .toList()

                listOf<Pair<String, String>>(
                    Pair(kids[0], things[1]),
                    Pair(kids[0], weight[1]),
                    Pair(kids[1], things[3]),
                    Pair(kids[1], weight[3]),
                    Pair(kids[2], things[2]),
                    Pair(kids[2], weight[0]),
                    Pair(kids[3], things[0]),
                    Pair(kids[3], weight[2]),
                )
            }
            PuzzleName.JAZZ_BANDS_SOLOS -> {
                val firstNames =
                    context.resources.getStringArray(R.array.jazz_band_solos_1_first_names).toList()
                val lastNames =
                    context.resources.getStringArray(R.array.jazz_band_solos_items_2_last_names)
                        .toList()
                val instruments =
                    context.resources.getStringArray(R.array.jazz_band_solos_items_3_instruments)
                        .toList()

                listOf<Pair<String, String>>(
                    Pair(firstNames[0], lastNames[4]),
                    Pair(firstNames[0], instruments[0]),
                    Pair(firstNames[1], lastNames[1]),
                    Pair(firstNames[1], instruments[3]),
                    Pair(firstNames[2], lastNames[0]),
                    Pair(firstNames[2], instruments[1]),
                    Pair(firstNames[3], lastNames[3]),
                    Pair(firstNames[3], instruments[2]),
                    Pair(firstNames[4], lastNames[2]),
                    Pair(firstNames[4], instruments[4]),
                )
            }
        }
    }
}
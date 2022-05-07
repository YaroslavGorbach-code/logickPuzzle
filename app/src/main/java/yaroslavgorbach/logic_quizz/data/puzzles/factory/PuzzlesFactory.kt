package yaroslavgorbach.logic_quizz.data.puzzles.factory

import yaroslavgorbach.logic_quizz.data.common.model.PuzzleName
import yaroslavgorbach.logic_quizz.data.puzzles.mapper.PuzzleAvailabilityProvider
import yaroslavgorbach.logic_quizz.data.puzzles.mapper.PuzzleNameToDescriptionMapper
import yaroslavgorbach.logic_quizz.data.puzzles.mapper.PuzzleNameToDifficultyMapper
import yaroslavgorbach.logic_quizz.data.puzzles.model.PuzzleItem

class PuzzlesFactory(
    private val nameToDescriptionMapper: PuzzleNameToDescriptionMapper,
    private val puzzleNameToDifficultyMapper: PuzzleNameToDifficultyMapper,
    private val puzzleAvailabilityProvider: PuzzleAvailabilityProvider,
) {

    private val names: List<PuzzleName> = PuzzleName.values().toList()

    fun create(): List<PuzzleItem> {
       return names.map { name ->
            when (name) {
                PuzzleName.SNACK_TIME -> PuzzleItem(
                    name = name,
                    description = nameToDescriptionMapper.map(name),
                    difficultLevel = puzzleNameToDifficultyMapper.map(name),
                    isAvailable = puzzleAvailabilityProvider.isAvailable(name)
                )
            }
        }
    }
}
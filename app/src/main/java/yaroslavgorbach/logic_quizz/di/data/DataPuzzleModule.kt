package yaroslavgorbach.logic_quizz.di.data

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yaroslavgorbach.logic_quizz.data.common.PuzzleRepo
import yaroslavgorbach.logic_quizz.data.common.PuzzleRepoImp
import yaroslavgorbach.logic_quizz.data.puzzle.factory.PuzzleCluesProvider
import yaroslavgorbach.logic_quizz.data.puzzle.factory.PuzzleFactory
import yaroslavgorbach.logic_quizz.data.puzzle.factory.PuzzleTitlesFactory
import yaroslavgorbach.logic_quizz.data.puzzles.factory.PuzzlesFactory
import yaroslavgorbach.logic_quizz.data.puzzles.mapper.PuzzleAvailabilityProvider
import yaroslavgorbach.logic_quizz.data.puzzles.mapper.PuzzleNameToDescriptionMapper
import yaroslavgorbach.logic_quizz.data.puzzles.mapper.PuzzleNameToDifficultyMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataPuzzleModule {

    @Provides
    fun providePuzzleTitlesFactory(app: Application): PuzzleTitlesFactory {
        return PuzzleTitlesFactory(app)
    }

    @Provides
    fun providePuzzleCluesProvider(app: Application): PuzzleCluesProvider {
        return PuzzleCluesProvider(app)
    }

    @Provides
    fun providePuzzleFactory(fac: PuzzleTitlesFactory, cluesProvider: PuzzleCluesProvider): PuzzleFactory {
        return PuzzleFactory(fac, cluesProvider)
    }

    @Provides
    fun providePuzzleNameToDescriptionMapper(app: Application): PuzzleNameToDescriptionMapper {
        return PuzzleNameToDescriptionMapper(app)
    }

    @Provides
    fun provideNameToDifficultyMapper(): PuzzleNameToDifficultyMapper {
        return PuzzleNameToDifficultyMapper()
    }

    @Provides
    fun providePuzzleAvailabilityProvider(): PuzzleAvailabilityProvider {
        return PuzzleAvailabilityProvider()
    }

    @Provides
    fun providePuzzlesFactory(
        puzzleNameToDescriptionMapper: PuzzleNameToDescriptionMapper,
        puzzleNameToDifficultyMapper: PuzzleNameToDifficultyMapper,
        puzzleAvailabilityProvider: PuzzleAvailabilityProvider
    ): PuzzlesFactory {
        return PuzzlesFactory(
            nameToDescriptionMapper = puzzleNameToDescriptionMapper,
            puzzleNameToDifficultyMapper = puzzleNameToDifficultyMapper,
            puzzleAvailabilityProvider = puzzleAvailabilityProvider
        )
    }

    @Singleton
    @Provides
    fun providePuzzleRepo(factory: PuzzleFactory, puzzlesFactory: PuzzlesFactory): PuzzleRepo {
        return PuzzleRepoImp(factory, puzzlesFactory)
    }

}
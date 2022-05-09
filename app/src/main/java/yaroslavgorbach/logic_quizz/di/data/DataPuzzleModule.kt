package yaroslavgorbach.logic_quizz.di.data

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yaroslavgorbach.logic_quizz.data.common.PuzzleRepo
import yaroslavgorbach.logic_quizz.data.common.PuzzleRepoImp
import yaroslavgorbach.logic_quizz.data.puzzle.factory.*
import yaroslavgorbach.logic_quizz.data.puzzles.PuzzleDataStore
import yaroslavgorbach.logic_quizz.data.puzzles.PuzzleDataStoreImp
import yaroslavgorbach.logic_quizz.data.puzzles.factory.PuzzleAvailabilityProvider
import yaroslavgorbach.logic_quizz.data.puzzles.factory.PuzzleCompleteProvider
import yaroslavgorbach.logic_quizz.data.puzzles.factory.PuzzlesFactory
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
    fun providePuzzleAvailabilityDataStore(app: Application): PuzzleDataStore {
        return PuzzleDataStoreImp(app)
    }

    @Provides
    fun providePuzzleCompleteDataStore(puzzleDataStore: PuzzleDataStore): PuzzleCompleteProvider {
        return PuzzleCompleteProvider(puzzleDataStore)
    }

    @Provides
    fun providePuzzleCluesProvider(app: Application): PuzzleCluesProvider {
        return PuzzleCluesProvider(app)
    }

    @Provides
    fun provideCorrectPairsProvider(app: Application): CorrectPairsProvider {
        return CorrectPairsProvider(app)
    }

    @Provides
    fun providePuzzleStoryProvider(app: Application): PuzzleStoryProvider {
        return PuzzleStoryProvider(app)
    }

    @Provides
    fun provideTableCellSizeProvider(): TableCellSizeProvider {
        return TableCellSizeProvider()
    }

    @Provides
    fun providePuzzleFactory(
        fac: PuzzleTitlesFactory,
        cluesProvider: PuzzleCluesProvider,
        correctPairsProvider: CorrectPairsProvider,
        puzzleStoryProvider: PuzzleStoryProvider,
        providerCellSizeProvider: TableCellSizeProvider
    ): PuzzleFactory {
        return PuzzleFactory(fac, cluesProvider, correctPairsProvider, puzzleStoryProvider, providerCellSizeProvider)
    }


    @Provides
    fun provideNameToDifficultyMapper(): PuzzleNameToDifficultyMapper {
        return PuzzleNameToDifficultyMapper()
    }

    @Provides
    fun providePuzzleAvailabilityProvider(puzzleDataStore: PuzzleDataStore): PuzzleAvailabilityProvider {
        return PuzzleAvailabilityProvider(puzzleDataStore)
    }

    @Provides
    fun providePuzzlesFactory(
        storyProvider: PuzzleStoryProvider,
        puzzleNameToDifficultyMapper: PuzzleNameToDifficultyMapper,
        puzzleAvailabilityProvider: PuzzleAvailabilityProvider,
        puzzleCompleteProvider: PuzzleCompleteProvider
    ): PuzzlesFactory {
        return PuzzlesFactory(
            puzzleStoryProvider = storyProvider,
            puzzleNameToDifficultyMapper = puzzleNameToDifficultyMapper,
            puzzleAvailabilityProvider = puzzleAvailabilityProvider,
            puzzleCompleteProvider = puzzleCompleteProvider
        )
    }

    @Singleton
    @Provides
    fun providePuzzleRepo(
        factory: PuzzleFactory,
        puzzlesFactory: PuzzlesFactory,
        puzzleDataStore: PuzzleDataStore
    ): PuzzleRepo {
        return PuzzleRepoImp(factory, puzzlesFactory, puzzleDataStore)
    }

}
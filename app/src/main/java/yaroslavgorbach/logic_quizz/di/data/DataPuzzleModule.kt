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
import yaroslavgorbach.logic_quizz.data.settings.local.SettingsDataStore
import yaroslavgorbach.logic_quizz.data.settings.local.SettingsDataStoreImp
import yaroslavgorbach.logic_quizz.data.settings.repo.RepoSettings
import yaroslavgorbach.logic_quizz.data.settings.repo.RepoSettingsImp
import yaroslavgorbach.logic_quizz.utills.AdManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataPuzzleModule {

    @Provides
    fun provideAdManagerFactory(app: Application): AdManager {
        return AdManager(app)
    }

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
    fun provideSettingsDataStore(app: Application): SettingsDataStore {
        return SettingsDataStoreImp(app)
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
    fun provideHintsProvider(app: Application): PuzzleHintsProvider {
        return PuzzleHintsProvider(app)
    }

    @Provides
    fun providePuzzleFactory(
        fac: PuzzleTitlesFactory,
        cluesProvider: PuzzleCluesProvider,
        correctPairsProvider: CorrectPairsProvider,
        puzzleStoryProvider: PuzzleStoryProvider,
        providerCellSizeProvider: TableCellSizeProvider,
        puzzleHintsProvider: PuzzleHintsProvider
    ): PuzzleFactory {
        return PuzzleFactory(
            fac,
            cluesProvider,
            puzzleHintsProvider,
            correctPairsProvider,
            puzzleStoryProvider,
            providerCellSizeProvider
        )
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

    @Singleton
    @Provides
    fun provideSettingsRepo(
        settingsDataStore: SettingsDataStore
    ): RepoSettings {
        return RepoSettingsImp(settingsDataStore)
    }

}
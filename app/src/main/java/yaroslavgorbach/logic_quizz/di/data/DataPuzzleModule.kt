package yaroslavgorbach.logic_quizz.di.data

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yaroslavgorbach.logic_quizz.data.puzzle.PuzzleRepo
import yaroslavgorbach.logic_quizz.data.puzzle.PuzzleRepoImp
import yaroslavgorbach.logic_quizz.data.puzzle.factory.PuzzleFactory
import yaroslavgorbach.logic_quizz.data.puzzle.factory.PuzzleTitlesFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataPuzzleModule {

    @Provides
    fun providePuzzleTitlesFactory(app: Application): PuzzleTitlesFactory {
        return PuzzleTitlesFactory(app)
    }


    @Provides
    fun providePuzzleFactory(fac: PuzzleTitlesFactory): PuzzleFactory {
        return PuzzleFactory(fac)
    }

    @Singleton
    @Provides
    fun providePuzzleRepo(factory: PuzzleFactory): PuzzleRepo {
        return PuzzleRepoImp(factory)
    }

}
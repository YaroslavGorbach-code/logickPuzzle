package yaroslavgorbach.logic_quizz.di.data

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yaroslavgorbach.logic_quizz.data.puzzle.PuzzleRepo
import yaroslavgorbach.logic_quizz.data.puzzle.PuzzleRepoImp
import yaroslavgorbach.logic_quizz.data.puzzle.factory.PuzzleFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataPuzzleModule {

    @Provides
    fun providePuzzleFactory(app: Application): PuzzleFactory {
        return PuzzleFactory(app)
    }

    @Singleton
    @Provides
    fun providePuzzleRepo(questions: PuzzleFactory): PuzzleRepo {
        return PuzzleRepoImp(questions)
    }

}
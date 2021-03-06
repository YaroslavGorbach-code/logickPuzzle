package yaroslavgorbach.logic_quizz.data.settings.repo

import kotlinx.coroutines.flow.Flow

interface RepoSettings {
    fun observeIsFirstAppOpen(): Flow<Boolean>

    suspend fun changeIsFirsAppOpen(isFirstOpen: Boolean)
}
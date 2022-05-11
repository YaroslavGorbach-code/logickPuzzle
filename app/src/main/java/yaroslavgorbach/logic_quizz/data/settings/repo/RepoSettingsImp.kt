package yaroslavgorbach.logic_quizz.data.settings.repo

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.logic_quizz.data.settings.local.SettingsDataStore

class RepoSettingsImp(private val localDataStore: SettingsDataStore) : RepoSettings {
    override fun observeIsFirstAppOpen(): Flow<Boolean> {
        return localDataStore.observeIsFirstAppOpen()
    }

    override suspend fun changeIsFirsAppOpen(isFirstOpen: Boolean) {
        localDataStore.changeIsFirstAppOpen(isFirstOpen)
    }
}
package yaroslavgorbach.logic_quizz.data.settings.local

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {
    suspend fun changeIsFirstAppOpen(isFirstAppOpen: Boolean)

    fun observeIsFirstAppOpen(): Flow<Boolean>
}
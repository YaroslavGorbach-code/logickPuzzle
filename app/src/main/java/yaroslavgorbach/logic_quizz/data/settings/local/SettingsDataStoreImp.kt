package yaroslavgorbach.logic_quizz.data.settings.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStoreImp(private val context: Context) : SettingsDataStore {

    companion object {
        private val IS_FIRST_APP_OPEN_KEY = booleanPreferencesKey("IS_FIRST_APP_OPEN_KEY")
    }

    override fun observeIsFirstAppOpen(): Flow<Boolean> {
        return context.settingsDataStore.data
            .map { prefs ->
                prefs[IS_FIRST_APP_OPEN_KEY] ?: true
            }
    }

    override suspend fun changeIsFirstAppOpen(isFirstAppOpen: Boolean) {
        context.settingsDataStore.edit { prefs ->
            prefs[IS_FIRST_APP_OPEN_KEY] = isFirstAppOpen
        }
    }
}
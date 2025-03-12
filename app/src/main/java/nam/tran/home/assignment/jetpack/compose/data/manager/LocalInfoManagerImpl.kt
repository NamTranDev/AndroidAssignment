package nam.tran.home.assignment.jetpack.compose.data.manager

import android.content.Context
import android.preference.PreferenceDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nam.tran.home.assignment.jetpack.compose.BuildConfig
import nam.tran.home.assignment.jetpack.compose.domain.manager.LocalInfoManager

class LocalInfoManagerImpl constructor(
    private val context : Context
) : LocalInfoManager {
    override suspend fun saveAppOnBoarding() {
        context.dataStore.edit { setting ->
            setting[PreferencesKey.APP_ONBOARDING] = true
        }
    }

    override fun readAppOnBoarding(): Flow<Boolean> {
        return context.dataStore.data.map { preference ->
            preference[PreferencesKey.APP_ONBOARDING] ?: false
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(BuildConfig.NAME_DATA_STORE)

private object PreferencesKey {
    private const val APP_ONBOARDING_KEY = "OnBoarding Key"

    val APP_ONBOARDING = booleanPreferencesKey(PreferencesKey.APP_ONBOARDING_KEY)
}
package dev.korryr.farmbrige.ui.features.auth.preference

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("auth_preferences")

@Singleton
class AuthPreferenceRepo @Inject constructor ( context: Context) {
    private val dataStore = context.dataStore

    companion object {
        private val KEY_LOGGED_iN = booleanPreferencesKey("is_logged_in")
        private val KEY_USER_ID = stringPreferencesKey("user_id")
    }

    suspend fun setLoggedIn(userId: String) {
        dataStore.edit { preferences ->
            preferences[KEY_LOGGED_iN] = true
            preferences[KEY_USER_ID] = userId
        }
    }

    suspend fun setLoggedOut() {
        dataStore.edit { preferences ->
            preferences[KEY_LOGGED_iN] = false
            preferences.remove(KEY_USER_ID)
        }
    }

    val isLoggedIn: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[KEY_LOGGED_iN] ?: false
    }

    /**
     * Emits:
     *   • null       = still loading from disk
     *   • true/false = real value once DataStore loads
     */
//    val isLoggedInState: StateFlow<Boolean?> =
//        dataStore.data
//            .map { prefs -> prefs[KEY_LOGGED_iN] }     // returns Boolean? (null if not set)
//            .stateIn(
//                scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
//                started = SharingStarted.Lazily,
//                initialValue = null
//            )

} //did here
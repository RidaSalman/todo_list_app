import androidx.datastore.preferences.core.edit

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataPreference(context: Context) {

    private val appContext = context.applicationContext

    suspend fun getStringData(key: Preferences.Key<String>): String =
        appContext.dataStore.data.map { preference ->
            preference[key] ?: ""
        }.first()

    suspend fun setStringData(key: Preferences.Key<String>, value: String) {
        appContext.dataStore.edit { preference ->
            preference[key] = value
        }
    }

    companion object {
        val Remember_UserName = stringPreferencesKey("user_name")
        val Remember_UserEmail = stringPreferencesKey("user_email")
        val Remember_UserPhone = stringPreferencesKey("user_phone")
    }

}
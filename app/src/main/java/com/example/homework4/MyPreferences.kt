package com.example.homework4

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.homework4.MyPreferences.PreferenceKeys.showRating

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "restaurant_settings")

class MyPreferences(private val context: Context) {
    private object PreferenceKeys {
        val showRating = booleanPreferencesKey("show_rating")
    }

    suspend fun updateShowRating(showRating: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.showRating] = showRating
        }
    }

    fun watchShowRating(): Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[PreferenceKeys.showRating] ?: false
    }
}

package com.example.paddel.onboarding

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "onboarding")

@Singleton
class OnboardingRepository @Inject constructor(@ApplicationContext context: Context) {
    private val hasSeenKey = booleanPreferencesKey("has_seen_onboarding")
    private val ds = context.dataStore

    val hasSeenOnboarding: Flow<Boolean> = ds.data.map { it[hasSeenKey] ?: false }

    suspend fun setSeen() {
        ds.edit { it[hasSeenKey] = true }
    }
}

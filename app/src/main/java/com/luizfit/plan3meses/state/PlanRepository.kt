package com.luizfit.plan3meses.state

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.luizfit.plan3meses.data.ProgressState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.planDataStore by preferencesDataStore(name = "plan_progress")

class PlanRepository(private val context: Context) {
    private object Keys {
        val completedIds = stringSetPreferencesKey("completed_ids")
        val weeklyWeight = stringPreferencesKey("weekly_weight")
        val weeklyAbdomen = stringPreferencesKey("weekly_abdomen")
    }

    val progress: Flow<ProgressState> = context.planDataStore.data.map { prefs ->
        ProgressState(
            completedIds = prefs[Keys.completedIds] ?: emptySet(),
            weeklyWeight = prefs[Keys.weeklyWeight].orEmpty(),
            weeklyAbdomen = prefs[Keys.weeklyAbdomen].orEmpty()
        )
    }

    suspend fun setCompleted(id: String, completed: Boolean) {
        context.planDataStore.edit { prefs ->
            val updated = (prefs[Keys.completedIds] ?: emptySet()).toMutableSet()
            if (completed) {
                updated.add(id)
            } else {
                updated.remove(id)
            }
            prefs[Keys.completedIds] = updated
        }
    }

    suspend fun setWeeklyWeight(value: String) {
        context.planDataStore.edit { prefs ->
            prefs[Keys.weeklyWeight] = value
        }
    }

    suspend fun setWeeklyAbdomen(value: String) {
        context.planDataStore.edit { prefs ->
            prefs[Keys.weeklyAbdomen] = value
        }
    }
}

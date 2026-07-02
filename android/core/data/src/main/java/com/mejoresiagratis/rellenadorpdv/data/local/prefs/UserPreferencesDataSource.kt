package com.mejoresiagratis.rellenadorpdv.data.local.prefs

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.mejoresiagratis.rellenadorpdv.domain.model.UserPreferences
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Equivalente a: rellenadorTema_v1, rellenadorEuOnly_v1, y la clave de
 * consentimiento RGPD (CONSENT_KEY) de rellenador-pro.html — auditoría §04.9.
 * `darkTheme = null` reproduce el mismo comportamiento que la web: si no
 * hay preferencia guardada, sigue el tema del sistema/OS.
 */
class UserPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    private object Keys {
        val DARK_THEME_SET = booleanPreferencesKey("dark_theme_set")
        val DARK_THEME_VALUE = booleanPreferencesKey("dark_theme_value")
        val EU_ONLY = booleanPreferencesKey("eu_only_mode")
        val CONSENT_REMEMBERED = booleanPreferencesKey("consent_remembered")
    }

    val preferences = dataStore.data.map { prefs ->
        UserPreferences(
            darkTheme = if (prefs[Keys.DARK_THEME_SET] == true) prefs[Keys.DARK_THEME_VALUE] else null,
            euOnlyMode = prefs[Keys.EU_ONLY] ?: false,
            consentRemembered = prefs[Keys.CONSENT_REMEMBERED] ?: false,
        )
    }

    suspend fun setDarkTheme(enabled: Boolean?) {
        dataStore.edit { prefs ->
            if (enabled == null) {
                prefs[Keys.DARK_THEME_SET] = false
            } else {
                prefs[Keys.DARK_THEME_SET] = true
                prefs[Keys.DARK_THEME_VALUE] = enabled
            }
        }
    }

    suspend fun setEuOnlyMode(enabled: Boolean) {
        dataStore.edit { it[Keys.EU_ONLY] = enabled }
    }

    suspend fun setConsentRemembered(remembered: Boolean) {
        dataStore.edit { it[Keys.CONSENT_REMEMBERED] = remembered }
    }
}

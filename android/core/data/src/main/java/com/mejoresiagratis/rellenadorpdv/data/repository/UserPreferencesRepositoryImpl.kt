package com.mejoresiagratis.rellenadorpdv.data.repository

import com.mejoresiagratis.rellenadorpdv.data.local.prefs.UserPreferencesDataSource
import com.mejoresiagratis.rellenadorpdv.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataSource: UserPreferencesDataSource,
) : UserPreferencesRepository {
    override val preferences = dataSource.preferences
    override suspend fun setDarkTheme(enabled: Boolean?) = dataSource.setDarkTheme(enabled)
    override suspend fun setEuOnlyMode(enabled: Boolean) = dataSource.setEuOnlyMode(enabled)
    override suspend fun setConsentRemembered(remembered: Boolean) = dataSource.setConsentRemembered(remembered)
}

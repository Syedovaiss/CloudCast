package com.ovais.cloudcast.home.domain

import com.ovais.cloudcast.core.domain.usecase.SuspendParameterizedUseCase
import com.ovais.cloudcast.settings.data.SettingsManager

interface UpdateCityUseCase : SuspendParameterizedUseCase<String, Boolean>

class DefaultUpdateCityUseCase(
    private val settingsManager: SettingsManager
) : UpdateCityUseCase {
    override suspend fun invoke(param: String): Boolean {
        return settingsManager.updateCurrentCity(param)
    }
}
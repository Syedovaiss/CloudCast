package com.ovais.cloudcast.home.domain

import com.ovais.cloudcast.core.domain.usecase.SuspendUseCase
import com.ovais.cloudcast.settings.data.SettingsManager

interface GetCurrentCityUseCase : SuspendUseCase<String>

class DefaultGetCurrentCityUseCase(
    private val settingsManager: SettingsManager
) : GetCurrentCityUseCase {
    override suspend fun invoke(): String {
        return settingsManager.getCurrentCity()
    }
}
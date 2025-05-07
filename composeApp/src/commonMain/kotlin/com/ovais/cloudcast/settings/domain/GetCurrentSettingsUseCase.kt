package com.ovais.cloudcast.settings.domain

import com.ovais.cloudcast.core.data.database.AppSettings
import com.ovais.cloudcast.core.domain.usecase.SuspendUseCase
import com.ovais.cloudcast.settings.data.SettingsManager

interface GetCurrentSettingsUseCase : SuspendUseCase<AppSettings>

class DefaultGetCurrentSettingsUseCase(
    private val settingsManager: SettingsManager
) : GetCurrentSettingsUseCase {
    override suspend fun invoke(): AppSettings {
        return AppSettings(
            id = settingsManager.getId(),
            unitType = settingsManager.getUnitType().toString(),
            hasAQIEnabled = settingsManager.hasAQIEnabled(),
            notificationEnabled = false,
            measuringUnit = settingsManager.getMeasuringUnit().toString()
        )
    }
}
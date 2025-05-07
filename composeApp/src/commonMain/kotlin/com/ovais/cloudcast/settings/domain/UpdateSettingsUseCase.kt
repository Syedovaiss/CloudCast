package com.ovais.cloudcast.settings.domain

import com.ovais.cloudcast.core.domain.usecase.SuspendParameterizedUseCase
import com.ovais.cloudcast.settings.data.SettingsRepository
import com.ovais.cloudcast.settings.data.UpdateSettingsResult

interface UpdateSettingsUseCase : SuspendParameterizedUseCase<SettingType, UpdateSettingsResult>

class DefaultUpdateSettingsUseCase(
    private val settingsRepository: SettingsRepository
) : UpdateSettingsUseCase {
    override suspend fun invoke(param: SettingType): UpdateSettingsResult {
        return when (param) {
            is SettingType.AQISettings -> settingsRepository.updateAQI(param.isEnabled)
            is SettingType.MeasuringUnitSetting -> settingsRepository.updateMeasuringUnit(param.type)
            is SettingType.TemperatureSetting -> settingsRepository.updateUnitType(param.type)
        }
    }
}
package com.ovais.cloudcast.home.domain

import com.ovais.cloudcast.core.data.dto.MeasuringUnit
import com.ovais.cloudcast.core.data.dto.UnitType
import com.ovais.cloudcast.core.domain.usecase.SuspendUseCase
import com.ovais.cloudcast.settings.data.SettingsManager

interface GetWeatherConfigurationUseCase : SuspendUseCase<WeatherConfiguration>

class DefaultGetWeatherConfigurationUseCase(
    private val settingsManager: SettingsManager
) : GetWeatherConfigurationUseCase {
    override suspend fun invoke(): WeatherConfiguration {
        return WeatherConfiguration(
            isUnitTypeC = settingsManager.getUnitType() == UnitType.C,
            isKPHEnabled = settingsManager.getMeasuringUnit() == MeasuringUnit.KPH
        )
    }
}
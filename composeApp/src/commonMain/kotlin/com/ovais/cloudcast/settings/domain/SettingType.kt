package com.ovais.cloudcast.settings.domain

import com.ovais.cloudcast.core.data.dto.MeasuringUnit
import com.ovais.cloudcast.core.data.dto.UnitType

sealed interface SettingType {
    data class MeasuringUnitSetting(val type: MeasuringUnit) : SettingType
    data class AQISettings(val isEnabled: Boolean) : SettingType
    data class TemperatureSetting(val type: UnitType) : SettingType
}
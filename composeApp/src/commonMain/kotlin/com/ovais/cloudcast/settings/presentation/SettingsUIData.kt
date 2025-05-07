package com.ovais.cloudcast.settings.presentation

import com.ovais.cloudcast.core.data.database.AppSettings
import com.ovais.cloudcast.core.data.dto.MeasuringUnit
import com.ovais.cloudcast.core.data.dto.UnitType
import com.ovais.cloudcast.core.data.dto.asMeasuringUnit
import com.ovais.cloudcast.core.data.dto.asUnitType

data class SettingsUIData(
    val isFEnabled: Boolean,
    val isKPHEnabled: Boolean,
    val hasAQIEnabled: Boolean
) {
    companion object {
        val default = SettingsUIData(
            isFEnabled = AppSettings.default.unitType.asUnitType == UnitType.F,
            isKPHEnabled = AppSettings.default.measuringUnit.asMeasuringUnit == MeasuringUnit.KPH,
            hasAQIEnabled = AppSettings.default.hasAQIEnabled
        )
    }
}
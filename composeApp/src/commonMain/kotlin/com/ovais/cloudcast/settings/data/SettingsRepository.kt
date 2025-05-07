package com.ovais.cloudcast.settings.data

import com.ovais.cloudcast.core.data.dto.MeasuringUnit
import com.ovais.cloudcast.core.data.dto.UnitType

interface SettingsRepository {
    suspend fun updateMeasuringUnit(unit: MeasuringUnit): UpdateSettingsResult
    suspend fun updateUnitType(type: UnitType): UpdateSettingsResult
    suspend fun updateAQI(enabled: Boolean): UpdateSettingsResult
}


class DefaultSettingsRepository(
    private val settingsManager: SettingsManager
) : SettingsRepository {
    override suspend fun updateMeasuringUnit(unit: MeasuringUnit): UpdateSettingsResult {
        val result = settingsManager.updateMeasuringUnit(unit)
        return if (result) UpdateSettingsResult.Updated else UpdateSettingsResult.Failure("Failed to update settings")
    }

    override suspend fun updateUnitType(type: UnitType): UpdateSettingsResult {
        val result = settingsManager.updateUnitType(type)
        return if (result) UpdateSettingsResult.Updated else UpdateSettingsResult.Failure("Failed to update settings")

    }

    override suspend fun updateAQI(enabled: Boolean): UpdateSettingsResult {
        val result = settingsManager.updateAQISetting(enabled)
        return if (result) UpdateSettingsResult.Updated else UpdateSettingsResult.Failure("Failed to update settings")

    }
}
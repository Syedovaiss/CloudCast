package com.ovais.cloudcast.fake

import com.ovais.cloudcast.core.data.dto.MeasuringUnit
import com.ovais.cloudcast.core.data.dto.UnitType
import com.ovais.cloudcast.settings.data.SettingsManager


class FakeSettingsManager : SettingsManager {
    var mockUnitType: UnitType = UnitType.C
    var mockMeasuringUnit: MeasuringUnit = MeasuringUnit.KPH
    var mockHasAQIEnabled: Boolean = false

    override suspend fun getId(): Int = 1

    override suspend fun getUnitType(): UnitType = mockUnitType

    override suspend fun hasAQIEnabled(): Boolean = mockHasAQIEnabled

    override suspend fun getMeasuringUnit(): MeasuringUnit = mockMeasuringUnit

    override suspend fun updateUnitType(type: UnitType): Boolean = true

    override suspend fun updateMeasuringUnit(type: MeasuringUnit): Boolean = true

    override suspend fun updateAQISetting(enabled: Boolean): Boolean = true
}
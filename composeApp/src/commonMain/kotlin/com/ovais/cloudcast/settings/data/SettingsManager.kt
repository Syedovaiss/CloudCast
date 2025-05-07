package com.ovais.cloudcast.settings.data

import com.ovais.cloudcast.core.data.database.AppSettings
import com.ovais.cloudcast.core.data.database.AppSettingsDao
import com.ovais.cloudcast.core.data.dto.MeasuringUnit
import com.ovais.cloudcast.core.data.dto.UnitType
import com.ovais.cloudcast.core.data.dto.asMeasuringUnit
import com.ovais.cloudcast.core.data.dto.asUnitType
import com.ovais.cloudcast.utils.orFalse
import com.ovais.cloudcast.utils.orOne

interface SettingsManager {
    suspend fun getId(): Int
    suspend fun getUnitType(): UnitType
    suspend fun hasAQIEnabled(): Boolean
    suspend fun getMeasuringUnit(): MeasuringUnit
    suspend fun updateUnitType(type: UnitType): Boolean
    suspend fun updateMeasuringUnit(type: MeasuringUnit): Boolean
    suspend fun updateAQISetting(enabled: Boolean): Boolean
}

class DefaultSettingsManager(
    private val appSettingsDao: AppSettingsDao
) : SettingsManager {

    override suspend fun getId(): Int {
        return appSettingsDao.getSettings()?.id.orOne
    }

    override suspend fun getUnitType(): UnitType {
        return appSettingsDao.getSettings()?.unitType?.asUnitType ?: UnitType.C
    }

    override suspend fun hasAQIEnabled(): Boolean {
        return appSettingsDao.getSettings()?.hasAQIEnabled.orFalse
    }

    override suspend fun getMeasuringUnit(): MeasuringUnit {
        return appSettingsDao.getSettings()?.measuringUnit?.asMeasuringUnit ?: MeasuringUnit.KPH
    }

    override suspend fun updateAQISetting(enabled: Boolean): Boolean {
        return try {
            appSettingsDao.getSettings()?.let { settings ->
                val newSettings = settings.copy(
                    hasAQIEnabled = enabled
                )
                appSettingsDao.updateSettings(newSettings)
            } ?: run {
                val settings = AppSettings.default.copy(
                    hasAQIEnabled = enabled
                )
                appSettingsDao.insertSettings(settings)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun updateMeasuringUnit(type: MeasuringUnit): Boolean {
        return try {
            appSettingsDao.getSettings()?.let { settings ->
                val newSettings = settings.copy(
                    measuringUnit = type.toString()
                )
                appSettingsDao.updateSettings(newSettings)
            } ?: run {
                val settings = AppSettings.default.copy(
                    measuringUnit = type.toString()
                )
                appSettingsDao.insertSettings(settings)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun updateUnitType(type: UnitType): Boolean {
        return try {
            appSettingsDao.getSettings()?.let { settings ->
                val newSettings = settings.copy(
                    unitType = type.toString()
                )
                appSettingsDao.updateSettings(newSettings)
            } ?: run {
                val settings = AppSettings.default.copy(
                    unitType = type.toString()
                )
                appSettingsDao.insertSettings(settings)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
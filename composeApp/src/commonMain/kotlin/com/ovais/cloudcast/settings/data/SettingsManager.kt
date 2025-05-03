package com.ovais.cloudcast.settings.data

import com.ovais.cloudcast.core.data.database.AppSettingsDao
import com.ovais.cloudcast.core.data.dto.UnitType
import com.ovais.cloudcast.core.data.dto.asUnitType
import com.ovais.cloudcast.utils.orFalse

interface SettingsManager {
    suspend fun getUnitType(): UnitType
    suspend fun hasAQIEnabled(): Boolean
}

class DefaultSettingsManager(
    private val appSettingsDao: AppSettingsDao
) : SettingsManager {

    override suspend fun getUnitType(): UnitType {
        return appSettingsDao.getSettings()?.unitType?.asUnitType ?: UnitType.C
    }

    override suspend fun hasAQIEnabled(): Boolean {
        return appSettingsDao.getSettings()?.hasAQIEnabled.orFalse
    }

}
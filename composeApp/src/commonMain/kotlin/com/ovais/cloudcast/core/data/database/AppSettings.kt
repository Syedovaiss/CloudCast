package com.ovais.cloudcast.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ovais.cloudcast.core.data.dto.MeasuringUnit
import com.ovais.cloudcast.core.data.dto.UnitType

@Entity(tableName = "app_settings")
data class AppSettings(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val unitType: String,
    val hasAQIEnabled: Boolean,
    val notificationEnabled: Boolean,
    val measuringUnit: String
) {
    companion object {
        val default = AppSettings(
            id = 1,
            unitType = UnitType.C.toString(),
            hasAQIEnabled = false,
            notificationEnabled = false,
            measuringUnit = MeasuringUnit.KPH.toString()
        )
    }
}
package com.ovais.cloudcast.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_settings")
data class AppSettings(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val unitType: String,
    val hasAQIEnabled: Boolean,
    val notificationEnabled: Boolean,
    val measuringUnit: String
)

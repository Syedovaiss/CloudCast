package com.ovais.cloudcast.core.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [AppSettings::class],
    version = 2
)
@ConstructedBy(BookDatabaseConstructor::class)
abstract class CloudCastDatabase : RoomDatabase() {
    abstract val appSettingDao: AppSettingsDao

    companion object {
        const val DB_NAME = "cloud_cast.db"
    }
}
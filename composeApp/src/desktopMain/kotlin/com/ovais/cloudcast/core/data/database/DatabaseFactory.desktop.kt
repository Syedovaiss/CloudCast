package com.ovais.cloudcast.core.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<CloudCastDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "CloudCast")
            os.contains("mac") -> File(userHome, "Library/Application Support/CloudCast")
            else -> File(userHome, ".local/share/CloudCast")
        }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, CloudCastDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}
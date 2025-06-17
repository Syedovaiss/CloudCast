package com.ovais.cloudcast.core.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File


private const val WINDOWS = "win"
private const val MAC = "mac"
private const val USER_HOME = "user.home"
private const val OS_NAME = "os.name"
private const val CLOUD_CAST = "CloudCast"

private enum class Directory(val path: String) {
    Windows("APPDATA"),
    MAC("Library/Application Support/CloudCast"),
    OTHER(".local/share/CloudCast")
}

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<CloudCastDatabase> {
        val os = System.getProperty(OS_NAME).lowercase()
        val userHome = System.getProperty(USER_HOME)
        val appDataDir = when {
            os.contains(WINDOWS) -> File(System.getenv(Directory.Windows.path), CLOUD_CAST)
            os.contains(MAC) -> File(userHome, Directory.MAC.path)
            else -> File(userHome, Directory.OTHER.path)
        }
        if (appDataDir.exists().not()) {
            appDataDir.mkdirs()
        }


        val dbFile = File(appDataDir, CloudCastDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}
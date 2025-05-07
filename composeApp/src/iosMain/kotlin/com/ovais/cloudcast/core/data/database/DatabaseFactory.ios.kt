package com.ovais.cloudcast.core.data.database


import androidx.room.Room
import androidx.room.RoomDatabase
import com.ovais.cloudcast.core.data.database.CloudCastDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<CloudCastDatabase> {
        val dbFile = documentDirectory() + "/${CloudCastDatabase.DB_NAME}"
        return Room.databaseBuilder<CloudCastDatabase>(
            name = dbFile
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )
        return requireNotNull(documentDirectory?.path)
    }
}
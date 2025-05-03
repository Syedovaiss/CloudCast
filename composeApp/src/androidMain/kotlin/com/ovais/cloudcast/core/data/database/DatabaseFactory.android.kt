package com.ovais.cloudcast.core.data.database


import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ovais.cloudcast.core.data.database.CloudCastDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<CloudCastDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(CloudCastDatabase.DB_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}
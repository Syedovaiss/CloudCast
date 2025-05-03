package com.ovais.cloudcast.core.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor : RoomDatabaseConstructor<CloudCastDatabase> {
    override fun initialize(): CloudCastDatabase
}
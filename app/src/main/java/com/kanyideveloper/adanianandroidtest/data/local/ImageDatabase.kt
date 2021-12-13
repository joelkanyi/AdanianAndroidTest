package com.kanyideveloper.adanianandroidtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ImageEntity::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {
    abstract val dao: ImageDao
}
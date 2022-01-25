package com.example.prep2.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PackageName::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun packageDao(): PackageDao
}


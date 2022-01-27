package com.example.prep2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prep2.model.PackageDao
import com.example.prep2.model.PackageInfo

@Database(entities = [PackageInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun packageDao(): PackageDao
}


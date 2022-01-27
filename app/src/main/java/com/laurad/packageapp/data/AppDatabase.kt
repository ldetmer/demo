package com.laurad.packageapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.laurad.packageapp.model.PackageDao
import com.laurad.packageapp.model.PackageInfo

@Database(entities = [PackageInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun packageDao(): PackageDao
}


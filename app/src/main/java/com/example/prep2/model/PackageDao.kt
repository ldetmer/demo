package com.example.prep2.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PackageDao {
    @Query("SELECT * FROM PackageInfo")
    fun getAll(): List<PackageInfo>

    @Insert
    fun insertAll( packages: List<PackageInfo>)

    @Query ("SELECT * FROM PackageInfo WHERE name = :name")
    fun lookupPackageInfoByName(name: String): PackageInfo?

    @Delete
    fun delete(packageName: PackageInfo)

    @Query("DELETE FROM PackageInfo")
    fun deleteAll()
}

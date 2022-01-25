package com.example.prep2.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PackageDao {
    @Query("SELECT * FROM PackageName")
    fun getAll(): List<PackageName>

    @Insert
    fun insertAll( packages: List<PackageName>)


    @Delete
    fun delete(packageName: PackageName)

    @Query("DELETE FROM PackageName")
    fun deleteAll()
}

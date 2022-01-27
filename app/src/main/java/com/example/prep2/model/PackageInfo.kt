package com.example.prep2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PackageInfo (
    @PrimaryKey
    @SerializedName("package_name")
    val name: String,
    @SerializedName("version_code")
    val version: String,
    @SerializedName("minimum_version")
    val minimunVersion: String
)

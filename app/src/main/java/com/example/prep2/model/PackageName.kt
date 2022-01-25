package com.example.prep2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PackageName (
@PrimaryKey
    val name: String
)

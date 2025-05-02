package com.example.homework4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "location_of_restaurant") val location: String?,
    @ColumnInfo(name = "rating_of_restaurant") val rating: Double?
)
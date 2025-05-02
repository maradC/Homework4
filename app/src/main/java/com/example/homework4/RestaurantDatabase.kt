package com.example.homework4

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Restaurant::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun restaurantDAO(): RestaurantDAO
}
package com.example.homework4

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MyApplication : Application() {
        companion object {
            lateinit var restaurantRepository: RestaurantRepository
        }

        override fun onCreate() {
            super.onCreate()

            val myPreferences = MyPreferences(applicationContext)

            val restaurantDatabase = runBlocking(Dispatchers.IO) {
                Room.databaseBuilder(
                    applicationContext,
                    RestaurantDatabase::class.java, "restaurant_database"
                ).build()
            }
            restaurantRepository = RestaurantRepository(restaurantDatabase, myPreferences)
        }
    }
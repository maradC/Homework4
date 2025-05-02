package com.example.homework4

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RestaurantRepository(private val restaurantDatabase: RestaurantDatabase, private val myPreferences: MyPreferences) {

    private val restaurantDao = restaurantDatabase.restaurantDAO()

    // Room DB Actions
    suspend fun getAllRestaurants(): Flow<List<Restaurant>> {
        return restaurantDao.selectAll()
    }

    suspend fun addRestaurant(restaurant: Restaurant) {
        restaurantDao.insert(restaurant)
    }

    // Preferences DataStore Action
    suspend fun getShowRating(): Flow<Boolean> {
        return myPreferences.watchShowRating()
    }

    suspend fun setShowRating(showRating: Boolean) {
        myPreferences.updateShowRating(showRating)
    }
}

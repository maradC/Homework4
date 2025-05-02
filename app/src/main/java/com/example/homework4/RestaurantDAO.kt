package com.example.homework4

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDAO {
    @Query("SELECT * FROM restaurant")
    fun selectAll() : Flow<List<Restaurant>>

    @Insert
    suspend fun insert(restaurant: Restaurant)

    @Query("DELETE FROM restaurant")
    suspend fun deleteAll()

}
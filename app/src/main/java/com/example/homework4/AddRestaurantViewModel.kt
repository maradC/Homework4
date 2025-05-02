package com.example.homework4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest

class AddRestaurantViewModel(private val repository: RestaurantRepository) : ViewModel() {

    val restaurantName = MutableStateFlow("")
    val cuisine = MutableStateFlow("")
    val rating = MutableStateFlow("")

    fun addRestaurant() {
        val name = restaurantName.value
        val cuisineType = cuisine.value
        val ratingValue = rating.value.toDoubleOrNull() ?: 0.0

        if (name.isNotBlank() && cuisineType.isNotBlank()) {
            val restaurant = Restaurant(
                name = name,
                cuisine = cuisineType,
                rating = ratingValue
            )

            viewModelScope.launch {
                repository.addRestaurant(restaurant)
                // Reset form fields
                restaurantName.value = ""
                cuisine.value = ""
                rating.value = ""
            }
        }
    }
}
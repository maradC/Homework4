package com.example.homework4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest


class RestaurantListViewModel(private val repository: RestaurantRepository) : ViewModel() {

    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> = _restaurants.asStateFlow()

    private val _showRating = MutableStateFlow(false)
    val showRating: StateFlow<Boolean> = _showRating.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllRestaurants().collectLatest { restaurantList ->
                _restaurants.value = restaurantList
            }
        }

        viewModelScope.launch {
            repository.getShowRating().collectLatest { showRatingValue ->
                _showRating.value = showRatingValue
            }
        }
    }
}
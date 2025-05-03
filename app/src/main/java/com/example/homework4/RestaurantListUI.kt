package com.example.homework4

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RestaurantListScreen(modifier: Modifier = Modifier, viewModel: RestaurantListViewModel = viewModel { RestaurantListViewModel(MyApplication.restaurantRepository) }) {
    val restaurants by viewModel.restaurants.collectAsState()
    val showRating by viewModel.showRating.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        RestaurantListSection(restaurants, showRating)
    }
}

@Composable
private fun RestaurantListSection(restaurants: List<Restaurant>, showRating: Boolean) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 30.dp
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
            Heading("Restaurants")
            Spacer(modifier = Modifier.height(16.dp))
            RestaurantList(restaurants, showRating)
        }
    }
}

@Composable
private fun RestaurantList(restaurants: List<Restaurant>, showRating: Boolean) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(restaurants) { restaurant ->
            RestaurantItem(restaurant, showRating)
        }
    }
}

@Composable
private fun RestaurantItem(restaurant: Restaurant, showRating: Boolean) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Location: ${restaurant.location}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Magenta
            )

            if (showRating) {
                Text(
                    text = "Rating: ${String.format("%.1f", restaurant.rating)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Magenta
                )
            }
        }
    }
}

@Composable
private fun Heading(heading: String) {
    Text(
        text = heading,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        softWrap = false,
        style = MaterialTheme.typography.headlineMedium
    )
}
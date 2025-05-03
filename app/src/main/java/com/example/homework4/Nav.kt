package com.example.homework4

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier ){
    NavHost(navController = navController, startDestination = "restaurantList", modifier = modifier) {
        composable(route = "restaurantList") {
            RestaurantListScreen()
        }
        composable(route = "restaurantAdd") {
            AddRestaurantScreen()

        }
        composable(route = "restaurantSettings") {
            SettingsScreen()
        }
    }
}
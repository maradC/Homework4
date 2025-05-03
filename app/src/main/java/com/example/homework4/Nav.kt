package com.example.homework4

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// Optional but recommended: Define routes in a structured way
sealed class Screen(val route: String) {
    object RestaurantList : Screen("restaurantList")
    object AddRestaurant : Screen("addRestaurant")
    object Settings : Screen("settings")
}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier ){
    NavHost(navController = navController, startDestination = Screen.RestaurantList.route, modifier = modifier) {
        composable(route = Screen.RestaurantList.route) {

            RestaurantListScreen()
        }

        // Route for the Add Restaurant Screen
        composable(route = Screen.AddRestaurant.route) {
            AddRestaurantScreen()

        }

        composable(route = Screen.Settings.route) {
            SettingsScreen()
        }
    }
}
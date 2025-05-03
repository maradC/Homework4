package com.example.homework4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar // Keep this import
import androidx.compose.material3.NavigationBarItem // Keep this import
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.homework4.ui.theme.Homework4Theme


data class NavItem(
    val title: String,
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector,
    val route: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Homework4Theme {
                val navItems = listOf(
                    NavItem(
                        title = "Restaurants",
                        iconSelected = Icons.Filled.Home,
                        iconUnselected = Icons.Outlined.Home,
                        route = "restaurantList"
                    ),
                    NavItem(
                        title = "Add",
                        iconSelected = Icons.Filled.Add,
                        iconUnselected = Icons.Outlined.Add,
                        route = "restaurantAdd"
                    )
                )

                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            navItems.forEach { item ->
                                val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = {
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (isSelected) item.iconSelected else item.iconUnselected,
                                            contentDescription = item.title
                                        )
                                    },
                                    label = { Text(item.title) }
                                )
                            } // End of forEach
                        } // End NavigationBar
                    }, // End bottomBar lambda
                    floatingActionButton = {
                        // Call the extracted FAB function
                        MyFAB(navController = navController)
                    }
                ) { paddingValues ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(paddingValues)
                    )
                } // End Scaffold
            } // End Theme
        } // End setContent
    } // End onCreate
} // End Activity


@Composable
fun MyFAB(navController: NavHostController) {
    FloatingActionButton(onClick = {
        navController.navigate("restaurantAdd") {
            // Optional configuration:
            // launchSingleTop = true
        }
    }) {
        Icon(Icons.Filled.Add, contentDescription = "Add Restaurant")
    }
}



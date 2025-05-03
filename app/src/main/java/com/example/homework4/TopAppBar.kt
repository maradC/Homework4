import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavController) {
    var showMenu by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = { Text(text = "My App") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            IconButton(onClick = {
                // Go back to previous screen
                navController.popBackStack()
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            // Action menu icon (three vertical dots)
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }

            // Dropdown menu that appears when action icon is clicked
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = {
                        // Navigate to settings screen
                        navController.navigate("settings_screen")
                        showMenu = false
                    },
                    leadingIcon = {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                )
            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            MyTopAppBar(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                Text(
                    text = "Main Screen Content",
                    fontSize = 20.sp
                )
            }
        }
    )
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            MyTopAppBar(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                Text(
                    text = "Settings Screen Content",
                    fontSize = 20.sp
                )
            }
        }
    )
}
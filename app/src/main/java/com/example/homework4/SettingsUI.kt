package com.example.homework4

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsScreen(modifier: Modifier = Modifier, viewModel: SettingsViewModel = viewModel { SettingsViewModel(MyApplication.restaurantRepository) }) {
    val showRating by viewModel.showRating.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        SettingsSection(showRating, viewModel::toggleShowRating)
    }
}

@Composable
private fun SettingsSection(
    showRating: Boolean,
    onToggleShowRating: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 30.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Heading("Settings")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {

                    Text(
                        text = "Show Rating = ${showRating}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Magenta
                    )
                }
            }
            Switch(
                checked = showRating,
                onCheckedChange = { onToggleShowRating() }
            )

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
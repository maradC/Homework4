package com.example.homework4

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddRestaurantScreen(modifier: Modifier = Modifier, viewModel: AddRestaurantViewModel = viewModel { AddRestaurantViewModel(MyApplication.restaurantRepository) }) {
    val restaurantName by viewModel.restaurantName.collectAsState()
    val location by viewModel.location.collectAsState()
    val rating by viewModel.rating.collectAsState()

    Surface(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 30.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Heading("Add Restaurant")
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = restaurantName,
                onValueChange = { viewModel.restaurantName.value = it },
                label = { Text("Restaurant Name") },
                modifier = Modifier.fillMaxWidth(0.8f),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = location,
                onValueChange = { viewModel.location.value = it },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth(0.8f),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = rating,
                onValueChange = { viewModel.rating.value = it },
                label = { Text("Rating") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(0.8f),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.addRestaurant() },
                modifier = Modifier.height(36.dp)
            ) {
                Text("Add", fontWeight = FontWeight.Bold)
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
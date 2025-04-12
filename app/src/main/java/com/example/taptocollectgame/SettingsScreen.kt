package com.example.taptocollectgame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel
) {
    val objectCount by settingsViewModel.objectCount.collectAsState()
    val objectType by settingsViewModel.objectType.collectAsState()
    val objectTypes = listOf("Coin", "Star", "Gem")

    var expanded by remember { mutableStateOf(false) } // For dropdown menu

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF54A4A6), // Teal
                        Color(0xFFDC3C4D)  // Red
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            // Title
            Text(
                text = "Settings",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF), // White
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Object Count Slider
            Text(
                text = "Number of Objects: $objectCount",
                fontSize = 20.sp,
                color = Color(0xFFFFFFFF) // White
            )
            Slider(
                value = objectCount.toFloat(),
                onValueChange = { settingsViewModel.setObjectCount(it.toInt()) },
                valueRange = 1f..10f,
                steps = 9,
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFFFFFFFF), // White
                    activeTrackColor = Color(0xFF54A4A6) // Teal
                )
            )

            // Object Type Dropdown
            Text(
                text = "Object Type: $objectType",
                fontSize = 20.sp,
                color = Color(0xFFFFFFFF) // White
            )
            Box {
                Button(
                    onClick = { expanded = !expanded },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)), // White
                ) {
                    Text(text = objectType, color = Color(0xFF000000)) // Black
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    objectTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(text = type) },
                            onClick = {
                                settingsViewModel.setObjectType(type)
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Sound Toggle (Optional)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = { navController.navigate("home") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)), // White
                ) {
                    Text(text = "Save & Back", color = Color(0xFF000000), fontSize = 18.sp) // Black
                }
            }

        }
    }
}




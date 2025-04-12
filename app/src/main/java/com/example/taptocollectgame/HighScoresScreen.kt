package com.example.taptocollectgame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HighScoresScreen(
    navController: NavController,
    viewModel: HighScoresViewModel
) {
    val highScores = viewModel.highScores.collectAsState()

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
        contentAlignment = Alignment.Center // Center all content vertically and horizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center, // Center content vertically
            modifier = Modifier.padding(16.dp)
        ) {
            // Title
            Text(
                text = "High Scores",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF), // White
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // High Scores List or Message
            if (highScores.value.isEmpty()) {
                Text(
                    text = "No High Scores Yet!",
                    fontSize = 20.sp,
                    color = Color(0xFFFFFFFF), // White
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(highScores.value) { score ->
                        HighScoreItem(score = score)
                    }
                }
            }

            // Back Button
            Button(
                onClick = { navController.navigateUp() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)), // White
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Back", color = Color(0xFF000000), fontSize = 18.sp) // Black
            }
        }
    }
}

@Composable
fun HighScoreItem(score: Int) {
    Text(
        text = "Score: $score",
        fontSize = 20.sp,
        color = Color(0xFFFFFFFF), // White
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}



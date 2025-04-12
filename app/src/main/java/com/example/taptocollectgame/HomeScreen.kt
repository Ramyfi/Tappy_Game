package com.example.taptocollectgame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
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
            )
    ) {
        // Game Title
        Text(
            text = "Tappy",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 64.dp)
        )

        // Game Subtitle
        Text(
            text = "Tap to Collect, Score Big!",
            fontSize = 18.sp,
            color = Color(0xFFFFFFFF).copy(alpha = 0.8f),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 128.dp)
        )

        // Buttons
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 32.dp)
        ) {
            // Start Game Button
            Button(
                onClick = { navController.navigate("game") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
                modifier = Modifier.fillMaxWidth(),
                elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.playarrow),
                    contentDescription = "Play Icon",
                    tint = Color(0xFF54A4A6) // Teal for the icon
                )
                Spacer(Modifier.width(8.dp))
                Text(text = "Start Game", color = Color(0XFF000000), fontSize = 18.sp)
            }

            // High Scores Button
            Button(
                onClick = { navController.navigate("highScores") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
                modifier = Modifier.fillMaxWidth(),
                elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.score),
                    contentDescription = "High Scores Icon",
                    tint = Color(0xFF54A4A6) // Teal for the icon
                )
                Spacer(Modifier.width(8.dp))
                Text(text = "High Scores", color = Color(0XFF000000), fontSize = 18.sp)
            }

            // How to Play Button
            Button(
                onClick = { navController.navigate("howToPlay") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
                modifier = Modifier.fillMaxWidth(),
                elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.help),
                    contentDescription = "How To Play Icon",
                    tint = Color(0xFFDC3C4D) // Red for the icon
                )
                Spacer(Modifier.width(8.dp))
                Text(text = "How to Play", color = Color(0XFF000000), fontSize = 18.sp)
            }

            // Settings Button
            Button(
                onClick = { navController.navigate("settings") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
                modifier = Modifier.fillMaxWidth(),
                elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.settings),
                    contentDescription = "Settings Icon",
                    tint = Color(0xFFDC3C4D) // Red for the icon
                )
                Spacer(Modifier.width(8.dp))
                Text(text = "Settings", color = Color(0XFF000000), fontSize = 18.sp)
            }
        }
    }
}


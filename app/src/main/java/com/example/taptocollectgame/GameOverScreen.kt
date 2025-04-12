package com.example.taptocollectgame

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource

@Composable
fun GameOverScreen(
    navController: NavController,
    gameViewModel: GameViewModel,
    highScoresRepository: HighScoresRepository
) {
    val finalScore by gameViewModel.score.collectAsState()

    // Add the final score to the high scores
    LaunchedEffect(Unit) {
        highScoresRepository.addHighScore(finalScore)
        Log.d("GameOverScreen", "Score added to high scores: $finalScore")
    }

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
            // Game Over Title
            Text(
                text = "Game Over!",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFFFFF),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Final Score Display
            Text(
                text = "Your Score: $finalScore",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFFFFFF),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Motivational Message
            Text(
                text = if (finalScore > 0) "Great Job! Try to beat your score!" else "Better luck next time!",
                fontSize = 18.sp,
                color = Color(0xFFFFFFFF),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Buttons Section
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Play Again Button
                Button(
                    onClick = {
                        gameViewModel.resetGame()
                        navController.navigate("game") {
                            popUpTo("game") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.replay),
                        contentDescription = "Home Icon",
                        tint = Color(0xFF54A4A6) // Teal for the icon
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(text = "Play Again", color = Color(0xFF000000), fontSize = 18.sp)
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

                // Home Button
                Button(
                    onClick = { navController.navigate("home") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.home),
                        contentDescription = "Home Icon",
                        tint = Color(0xFF54A4A6) // Teal for the icon
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(text = "Home", color = Color(0xFF000000), fontSize = 18.sp)
                }

            }
        }
    }
}


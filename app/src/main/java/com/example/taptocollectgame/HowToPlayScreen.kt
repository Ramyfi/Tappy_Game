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
fun HowToPlayScreen(navController: NavController) {
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
        // Title
        Text(
            text = "How to Play",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 64.dp)
        )

        // Instructions Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InstructionStep(
                stepNumber = 1,
                instruction = "Tap on the objects to collect them and earn points.",
                iconRes = R.drawable.score
            )
            InstructionStep(
                stepNumber = 2,
                instruction = "Collect as many points as possible before time runs out.",
                iconRes = R.drawable.timer
            )
            InstructionStep(
                stepNumber = 3,
                instruction = "Use the pause button to take a break, or the stop button to quit.",
                iconRes = R.drawable.pause
            )
            InstructionStep(
                stepNumber = 4,
                instruction = "Enjoy the game and challenge your high score!",
                iconRes = R.drawable.happy
            )
        }

        // Back Button
        Button(
            onClick = { navController.navigateUp() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        ) {
            Text(text = "Back", color = Color(0xFF000000), fontSize = 18.sp)
        }
    }
}

@Composable
fun InstructionStep(stepNumber: Int, instruction: String, iconRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = "Step Icon",
            tint = Color(0xFFFFFFFF),
            modifier = Modifier.size(32.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = "$stepNumber. $instruction",
            color = Color(0xFFFFFFFF),
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
    }
}


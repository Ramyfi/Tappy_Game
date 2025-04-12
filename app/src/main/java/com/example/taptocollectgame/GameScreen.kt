package com.example.taptocollectgame

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random
import kotlinx.coroutines.delay


@Composable
fun GameScreen(
    navController: NavController,
    gameViewModel: GameViewModel,
    settingsViewModel: SettingsViewModel
) {
    val score by gameViewModel.score.collectAsState()
    val timeLeft by gameViewModel.timeLeft.collectAsState()
    val objectCount by settingsViewModel.objectCount.collectAsState()
    val objectType by settingsViewModel.objectType.collectAsState()
    val positions = remember { mutableStateListOf<Pair<Int, Int>>() }

    var isPaused by remember { mutableStateOf(false) }
    var showQuitDialog by remember { mutableStateOf(false) }

    // Initialize positions for the objects
    LaunchedEffect(objectCount) {
        positions.clear()
        repeat(objectCount) {
            positions.add(
                Pair(
                    Random.nextInt(0, 300),
                    Random.nextInt(100, 600)
                )
            )
        }
    }

    // Start the game timer
    LaunchedEffect(Unit) {
        gameViewModel.startGame()
        while (true) {
            if (!isPaused) {
                gameViewModel.decrementTimer()
            }
            delay(1000L) // Timer ticks every second
        }
    }

    // Update object positions on timer tick
    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            for (i in positions.indices) {
                positions[i] = Pair(
                    Random.nextInt(0, 300),
                    Random.nextInt(100, 600)
                )
            }
        } else {
            navController.navigate("gameOver")
        }
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
            )
    ) {
        // Score and Time Display
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Score: $score",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top=32.dp)
            )
            Text(
                text = "Timer: $timeLeft",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top=32.dp)

            )
        }

        // Pause/Play and Stop Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            // Pause/Play Button
            IconButton(
                onClick = { isPaused = !isPaused }
            ) {
                Icon(
                    painter = painterResource(id = if (isPaused) R.drawable.playarrow else R.drawable.pause),
                    contentDescription = if (isPaused) "Play" else "Pause",
                    tint = Color(0xFFFFFFFF),
                    modifier = Modifier.size(48.dp)
                )
            }

            // Stop Button
            IconButton(
                onClick = {
                    isPaused = true // Pause the game
                    showQuitDialog = true // Show quit confirmation
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.stop),
                    contentDescription = "Quit",
                    tint = Color(0xFFFFFFFF) ,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        // Objects to Collect
        positions.forEachIndexed { index, position ->
            Image(
                painter = painterResource(
                    id = when (objectType) {
                        "Star" -> R.drawable.star
                        "Gem" -> R.drawable.gem
                        else -> R.drawable.coin
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .offset(
                        x = position.first.dp,
                        y = position.second.dp
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                if (!isPaused) { // Prevent scoring when paused
                                    gameViewModel.collectObject()
                                    positions[index] = Pair(
                                        Random.nextInt(0, 300),
                                        Random.nextInt(100, 600)
                                    )
                                }
                            }
                        )
                    },
                contentScale = ContentScale.Crop
            )
        }

// Quit Confirmation Dialog
        if (showQuitDialog) {
            AlertDialog(
                onDismissRequest = { showQuitDialog = false },
                title = {
                    Text(
                        text = "Quit Game?",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFDC3C4D), // Red for emphasis
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                },
                text = {
                    Text(
                        text = "Are you sure you want to quit the game? Your progress will be lost.",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showQuitDialog = false
                            navController.navigate("home") // Navigate to Home Screen
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF54A4A6)) // Teal
                    ) {
                        Text("Quit", color = Color.White, fontSize = 16.sp)
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = { showQuitDialog = false },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFDC3C4D)) // Red
                    ) {
                        Text("Cancel", fontSize = 16.sp)
                    }
                },
                shape = MaterialTheme.shapes.medium, // Rounded corners for modern look
                containerColor = Color(0xFFFFFFFF) // White background
            )
        }

    }
}




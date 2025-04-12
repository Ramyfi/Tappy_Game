package com.example.taptocollectgame

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun Navigation(navController: NavHostController) {
    val gameViewModel: GameViewModel = viewModel()
    val settingsViewModel: SettingsViewModel = viewModel()
    val highScoresRepository = HighScoresRepository(LocalContext.current)
    val highScoresViewModel = HighScoresViewModel(highScoresRepository)


    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("game") {
            LaunchedEffect(true) {
                gameViewModel.resetGame()
            }
            GameScreen(navController, gameViewModel, settingsViewModel) }
        composable("gameOver") { GameOverScreen(navController, gameViewModel, highScoresRepository = highScoresRepository) }
        composable("highScores") { HighScoresScreen(navController, highScoresViewModel) }
        composable("settings") { SettingsScreen(navController, settingsViewModel) }
        composable("howToPlay") { HowToPlayScreen(navController) }
    }
}

package com.example.taptocollectgame

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class HighScoresViewModel(private val repository: HighScoresRepository) : ViewModel() {
    // Expose high scores as a StateFlow
    val highScores: StateFlow<List<Int>> = repository.getHighScoresFlow()

}

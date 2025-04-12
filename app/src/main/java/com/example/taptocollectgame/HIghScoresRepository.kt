package com.example.taptocollectgame

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class HighScoresRepository(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("high_scores", Context.MODE_PRIVATE)

    private val highScoresFlow = MutableStateFlow(getHighScores())


    // Flow to expose high scores
    fun getHighScoresFlow(): StateFlow<List<Int>> = highScoresFlow

    // Retrieve scores from SharedPreferences
    private fun getHighScores(): List<Int> {
        val scores = sharedPreferences.getStringSet("scores", emptySet()) ?: emptySet()
        return scores.mapNotNull { it.toIntOrNull() }.sortedDescending()
    }

    // Add a new high score and update the list
    fun addHighScore(score: Int) {
        val scores = getHighScores().toMutableList()

        // Add the new score if it's not already in the list
        if (!scores.contains(score)) {
            scores.add(score)
        }

        // Keep only the top 3 scores
        val sortedScores = scores.sortedDescending().take(3)

        // Save the updated scores to SharedPreferences
        sharedPreferences.edit()
            .putStringSet("scores", sortedScores.map { it.toString() }.toSet())
            .apply()

        // Update the flow
        highScoresFlow.value = sortedScores
    }
}

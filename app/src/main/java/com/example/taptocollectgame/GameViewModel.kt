package com.example.taptocollectgame


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class GameViewModel : ViewModel() {
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _timeLeft = MutableStateFlow(30)
    val timeLeft: StateFlow<Int> = _timeLeft.asStateFlow()

    fun startGame() {
        _score.value = 0
        _timeLeft.value = 31
    }

    fun collectObject() {
        _score.value += 1
    }

    fun decrementTimer() {
        if (_timeLeft.value > 0) {
            _timeLeft.value -= 1
        }
    }

    fun resetGame() {
        _score.value = 0
        _timeLeft.value = 31
    }
}

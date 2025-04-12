package com.example.taptocollectgame

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel : ViewModel() {
    private val _objectCount = MutableStateFlow(5)
    val objectCount: StateFlow<Int> = _objectCount

    private val _objectType = MutableStateFlow("Coin")
    val objectType: StateFlow<String> = _objectType

    private val _isDropdownExpanded = MutableStateFlow(false)
    val isDropdownExpanded: StateFlow<Boolean> = _isDropdownExpanded


    private val _soundEnabled = MutableStateFlow(true)
    val soundEnabled: StateFlow<Boolean> = _soundEnabled

    // Function to toggle sound
    fun toggleSound(isEnabled: Boolean) {
        _soundEnabled.value = isEnabled
    }

    fun updateObjectCount(count: Int) {
        _objectCount.value = count
    }

    fun updateObjectType(type: String) {
        _objectType.value = type
    }

    fun setDropdownExpanded(expanded: Boolean) {
        _isDropdownExpanded.value = expanded
    }


    // Update the number of objects
    fun setObjectCount(count: Int) {
        _objectCount.value = count
    }

    // Update the type of object
    fun setObjectType(type: String) {
        _objectType.value = type
    }
}

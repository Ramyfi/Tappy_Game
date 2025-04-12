package com.example.taptocollectgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.taptocollectgame.ui.theme.TapToCollectGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TapToCollectGameTheme {
                val navController = rememberNavController()
                Navigation(navController)
            }
        }
    }
}

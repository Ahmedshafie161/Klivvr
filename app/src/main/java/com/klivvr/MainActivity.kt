package com.klivvr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import com.klivvr.core.designSystem.KlivvrTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val splashScreen = installSplashScreen()
        var keepSplashOnScreen by mutableStateOf(true)

        splashScreen.setKeepOnScreenCondition {
            keepSplashOnScreen
        }

        setContent {
            KlivvrTheme {
                KlivvrNavHost()
            }
        }

        lifecycleScope.launch {
            delay(2000)
            keepSplashOnScreen = false
        }
    }
}
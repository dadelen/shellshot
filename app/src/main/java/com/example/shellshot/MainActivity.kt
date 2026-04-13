package com.example.shellshot

import android.graphics.Color as AndroidColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shellshot.ui.MainViewModel
import com.example.shellshot.ui.MainViewModelFactory
import com.example.shellshot.ui.theme.ShellShotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = AndroidColor.TRANSPARENT

        val appContainer = (application as ShellShotApplication).appContainer

        setContent {
            val mainViewModel: MainViewModel = viewModel(
                factory = MainViewModelFactory(appContainer),
            )
            val systemDarkTheme = isSystemInDarkTheme()
            var darkTheme by rememberSaveable(systemDarkTheme) { mutableStateOf(systemDarkTheme) }
            val insetsController = WindowCompat.getInsetsController(window, window.decorView)

            SideEffect {
                insetsController.isAppearanceLightStatusBars = !darkTheme
            }

            ShellShotTheme(darkTheme = darkTheme) {
                ShellShotApp(
                    viewModel = mainViewModel,
                    darkTheme = darkTheme,
                    onToggleDarkTheme = { darkTheme = !darkTheme },
                )
            }
        }
    }
}

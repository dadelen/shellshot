package com.example.shellshot

import android.graphics.Color as AndroidColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shellshot.ui.MainViewModel
import com.example.shellshot.ui.MainViewModelFactory
import com.example.shellshot.ui.theme.ShellShotTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = AndroidColor.TRANSPARENT
        window.navigationBarColor = AndroidColor.TRANSPARENT

        val appContainer = (application as ShellShotApplication).appContainer

        setContent {
            val mainViewModel: MainViewModel = viewModel(
                factory = MainViewModelFactory(appContainer),
            )
            val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
            val insetsController = WindowCompat.getInsetsController(window, window.decorView)

            SideEffect {
                insetsController.isAppearanceLightStatusBars = !uiState.resolvedDarkTheme
            }

            ShellShotTheme(darkTheme = uiState.resolvedDarkTheme) {
                ShellShotApp(viewModel = mainViewModel)
            }
        }
    }
}

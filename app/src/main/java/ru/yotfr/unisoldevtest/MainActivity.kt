package ru.yotfr.unisoldevtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.yotfr.unisoldevtest.ui.root.RootScreen
import ru.yotfr.unisoldevtest.ui.theme.WallpaperTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            WallpaperTheme {
                RootScreen()
            }
        }
    }
}
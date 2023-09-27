package com.codandotv.streamplayerapp.core_shared_ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.codandotv.streamplayerapp.core_shared_ui.resources.Colors

@Composable
fun StreamPlayerTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    backgroundColor: Color? = null,
    onBackgroundColor: Color? = null,
    errorColor: Color? = null,
    content: @Composable () -> Unit
) {

    val colorScheme = getColorScheme(isDarkTheme)
    val colorBackground = backgroundColor ?: colorScheme.background
    val colorOnBackground = onBackgroundColor ?: colorScheme.onBackground
    val colorError = errorColor ?: colorScheme.error

    val newColorScheme = colorScheme.copy(
        background = colorBackground,
        onBackground = colorOnBackground,
        error = colorError,
        surface = colorBackground
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = newColorScheme.surface.copy(0.8f).toArgb()
            window.navigationBarColor = newColorScheme.surface.copy(0.8f).toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                isDarkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                isDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = newColorScheme,
        content = content,
    )
}

private fun getColorScheme(isDarkTheme: Boolean) =
    if (isDarkTheme) {
        Colors.DarkColors
    } else {
        Colors.LightColors
    }

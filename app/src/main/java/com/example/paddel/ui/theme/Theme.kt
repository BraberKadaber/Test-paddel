package com.example.paddel.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.BuildCompat

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary
)

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary
)

@Composable
fun PaddleTheme(
    darkTheme: Boolean = MaterialTheme.colorScheme.isLight.not(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}

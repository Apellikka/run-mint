package com.apellikka.runmint.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = runmint_theme_dark_primary,
    secondary = runmint_theme_dark_secondary,
    tertiary = runmint_theme_dark_tertiary,
    background = runmint_theme_dark_background
)

private val LightColorScheme = lightColorScheme(
    primary = runmint_theme_light_primary,
    secondary = runmint_theme_light_secondary,
    tertiary = runmint_theme_light_tertiary,
    background = runmint_theme_light_background
)

@Composable
fun RunMintTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    /* TODO: Improve DarkTheme colors.
    when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }*/

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
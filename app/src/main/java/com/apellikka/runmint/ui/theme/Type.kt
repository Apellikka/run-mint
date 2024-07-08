package com.apellikka.runmint.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = Typography().displayLarge.copy(
        fontWeight = FontWeight.Bold),

    headlineLarge = Typography().headlineLarge.copy(
        color = Color.Black,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold),

    labelLarge = Typography().labelLarge.copy(
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold),

    titleLarge = Typography().titleLarge.copy(
        color = Color.Black,
        fontSize = 28.sp,
        fontStyle = FontStyle.Italic),

    bodyLarge = Typography().bodyLarge.copy(
        color = Color.Black,
        fontSize = 25.sp,
        fontWeight = FontWeight.SemiBold),

    bodyMedium = Typography().bodyMedium.copy(
        color = Color.Black,
        fontSize = 20.sp)
)

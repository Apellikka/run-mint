package com.apellikka.runmint.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.apellikka.runmint.R

val Stalinist = FontFamily(
    Font(R.font.stalinist_one_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Stalinist,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 28.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Stalinist,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )
)
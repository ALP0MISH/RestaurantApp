    package com.example.restaurantapp.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantapp.R

val INTER_FONT = FontFamily(Font(R.font.inter))

val HK_GROTEST_FONT = FontFamily(
    Font(R.font.hk_grotesk_regular, FontWeight.Normal),
    Font(R.font.hk_grotesk_semi_bold, FontWeight.SemiBold),
    Font(R.font.hk_grotesk_medium, FontWeight.Medium),
    Font(R.font.hk_grotesk_bold, FontWeight.Bold),
    Font(R.font.hk_grotesk_italic, FontWeight.Thin),
    Font(R.font.hk_grotesk_light, FontWeight.Light)
)
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = HK_GROTEST_FONT,
        fontWeight = FontWeight.Normal,
        fontSize =  28.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = HK_GROTEST_FONT,
        fontWeight = FontWeight.Normal,
        fontSize =  26.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = HK_GROTEST_FONT,
        fontWeight = FontWeight.Normal,
        fontSize =  24.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = HK_GROTEST_FONT,
        fontWeight = FontWeight.Normal,
        fontSize =  22.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = HK_GROTEST_FONT,
        fontWeight = FontWeight.Normal,
        fontSize =  20.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = HK_GROTEST_FONT,
        fontWeight = FontWeight.Normal,
        fontSize =  18.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = HK_GROTEST_FONT,
        fontWeight = FontWeight.Normal,
        fontSize =  16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = HK_GROTEST_FONT,
        fontWeight = FontWeight.Normal,
        fontSize =  14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = HK_GROTEST_FONT,
        fontWeight = FontWeight.Normal,
        fontSize =  12.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = HK_GROTEST_FONT,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    labelMedium = TextStyle(
        fontFamily = HK_GROTEST_FONT,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp
    ),
    labelSmall = TextStyle(
        fontFamily = HK_GROTEST_FONT,
        fontWeight = FontWeight.Normal,
        fontSize = 2.sp
    )
)
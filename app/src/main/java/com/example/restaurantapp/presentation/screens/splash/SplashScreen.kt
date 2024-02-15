package com.example.restaurantapp.presentation.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.theme.Background
import com.example.restaurantapp.presentation.theme.BackgroundModal
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.DarkPlaceholder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SPLASH_SCREEN_SHOW_TIME = 3000L

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = Unit) {
        delay(SPLASH_SCREEN_SHOW_TIME)

    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_splash))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) DarkPlaceholder else BackgroundModal),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(15.dp)),
            progress = progress
        )
    }
}
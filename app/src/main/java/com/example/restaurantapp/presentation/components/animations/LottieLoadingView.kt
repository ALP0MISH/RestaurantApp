package com.example.restaurantapp.presentation.components.animations

import android.content.Context
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.restaurantapp.R


@Composable
fun LottieLoadingView(
    context: Context,
    file: String,
    modifier: Modifier = Modifier,
    iterations: Int = 10
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.working))
    LottieAnimation(
        composition,
        modifier = modifier.defaultMinSize(300.dp),
        iterations = iterations
    )
}

@Composable
fun LottieWorkingLoadingView(context: Context) {
    LottieLoadingView(
        context = context,
        file = "working.json",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}

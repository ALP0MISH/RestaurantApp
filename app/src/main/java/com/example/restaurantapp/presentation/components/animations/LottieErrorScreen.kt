package com.example.restaurantapp.presentation.components.animations

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.models.MenuUi
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark

@Composable
fun LottieErrorScreen(
    errorMassage: String,
    refreshClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_internet_error))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever
    )
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(15.dp)),
                progress = progress
            )
            androidx.compose.material3.Text(
                modifier = Modifier.padding(top = 8.dp),
                text = errorMassage,
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
            )
            TextButton(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .fillMaxWidth(0.7f)
                    .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary),
                onClick = { refreshClick() }
            ) {
                androidx.compose.material3.Text(
                    text = stringResource(id = R.string.refresh),
                    style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Composable
fun LottieBasketErrorScreen(
    refreshClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_internet_error))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever
    )
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(15.dp)),
                progress = progress
            )
            TextButton(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .fillMaxWidth(0.7f)
                    .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary),
                onClick = { refreshClick() }
            ) {
                androidx.compose.material3.Text(
                    text = stringResource(id = R.string.refresh),
                    style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Preview
@Composable
fun LottieErrorScreenPreview() {
    MaterialTheme {
        LottieErrorScreen(
            refreshClick = {},
            errorMassage = String()
        )
    }
}
package com.example.restaurantapp.presentation.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.presentation.theme.Background
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.ExtraLargeSpacing

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.height(ExtraLargeSpacing))
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = ExtraLargeSpacing),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
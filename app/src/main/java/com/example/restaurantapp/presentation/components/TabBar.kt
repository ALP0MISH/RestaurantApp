package com.example.restaurantapp.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.LargeSpacing

@Composable
fun TabBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .statusBarsPadding()
            .fillMaxWidth(),
        shadowElevation = 4.dp,
        tonalElevation = 4.dp,
        color = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary,
    ) {
        Box(
            modifier = Modifier
                .padding(LargeSpacing)
                .fillMaxWidth(),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
fun TabBarPreview() {
    MaterialTheme {
        TabBar(
            title = stringResource(id = R.string.welcome)
        )
    }

}

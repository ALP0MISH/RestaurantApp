package com.example.restaurantapp.presentation.screens.basket_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.BasketItem
import com.example.restaurantapp.presentation.components.animations.LottieBasketErrorScreen
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.ExtraLargeSpacing
import com.example.restaurantapp.presentation.theme.LargeSpacing
import com.example.restaurantapp.presentation.theme.MediumSpacing

@Composable
fun BasketScreen(
    navigateToDetailScreen: (String) -> Unit,
    uiState: BasketUIState,
    refreshClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary)
            .padding(top = LargeSpacing)
    ) {
        item {
            Text(
                modifier = Modifier.padding(ExtraLargeSpacing),
                text = stringResource(id = R.string.basket),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        item {
            if (uiState.fetchFromBasket.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MediumSpacing)
                        .padding(top = ExtraLargeSpacing),
                    contentAlignment = Alignment.Center
                ) {
                    LottieBasketErrorScreen(
                        refreshClick = refreshClick,
                        modifier = Modifier
                    )
                }
            } else {
                this@LazyColumn.items(
                    items = uiState.fetchFromBasket,
                    key = { uiState -> uiState.objectId },
                ) { menu ->
                    BasketItem(
                        modifier = Modifier,
                        title = menu.title,
                        description = menu.description,
                        price = menu.price,
                        objectId = menu.objectId,
                        gram = menu.gram,
                        image = menu.imageUrl,
                        rating = menu.rating,
                        navigateToDetailScreen = navigateToDetailScreen
                    )
                }
            }
        }
    }
}
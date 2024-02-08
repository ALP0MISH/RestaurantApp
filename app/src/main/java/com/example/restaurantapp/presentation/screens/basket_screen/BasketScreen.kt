package com.example.restaurantapp.presentation.screens.basket_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.restaurantapp.presentation.components.SearchItem
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.LargeSpacing

@Composable
fun BasketScreen(
    navigateToDetailScreen: (String, String) -> Unit,
    uiState: BasketUIState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary)
            .padding(top = LargeSpacing)
    ) {
        items(
            items = uiState.fetchFromBasket,
            key = { uiState -> uiState.title },
        ) { menu ->
            SearchItem(
                modifier = Modifier.background(Color.White),
                title = menu.title,
                description = menu.description,
                price = menu.price,
                objectId = String(),
                category = menu.categoryId,
                gram = menu.gram,
                image = menu.imageUrl,
                rating = menu.rating,
                navigateToDetailScreen = navigateToDetailScreen
            )
        }
    }
}
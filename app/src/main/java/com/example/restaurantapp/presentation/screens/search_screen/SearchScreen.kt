package com.example.restaurantapp.presentation.screens.search_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.IsVisibleItem
import com.example.restaurantapp.presentation.components.SearchItem
import com.example.restaurantapp.presentation.components.animations.SpacerHeight
import com.example.restaurantapp.presentation.screens.common.LoadingScreen
import com.example.restaurantapp.presentation.theme.BackgroundModal
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.ExtraLargeSpacing
import com.example.restaurantapp.presentation.theme.LargeSpacing

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    navigateToDetailScreen: (String, String) -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary)
            .padding(top = LargeSpacing)
    ) {
        item {
            Column(
                modifier = Modifier
            ) {
                Row(
                        modifier = Modifier.padding(ExtraLargeSpacing),
                ) {
                    Text(
                        text = stringResource(id = R.string.search),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                SpacerHeight(LargeSpacing)
                OutlinedTextField(
                    value = uiState.query,
                    onValueChange = onValueChange,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colorScheme.onBackground,
                        disabledTextColor = MaterialTheme.colorScheme.onBackground,
                        backgroundColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal,
                        leadingIconColor = MaterialTheme.colorScheme.onBackground,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onBackground,
                        focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                        disabledLabelColor = MaterialTheme.colorScheme.onBackground,
                        cursorColor = MaterialTheme.colorScheme.onBackground,
                        errorCursorColor = MaterialTheme.colorScheme.onBackground,
                        focusedIndicatorColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal,
                        unfocusedIndicatorColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal,
                        disabledIndicatorColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal,
                        errorIndicatorColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal,
                    ),
                    minLines = 1,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.menu_name),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = ExtraLargeSpacing),
                )
            }
            when {
                uiState.query.isEmpty() -> IsVisibleItem()
                uiState.menu.allItems.isEmpty() -> IsVisibleItem()
                uiState.isLoading -> LoadingScreen()
                uiState.query.isNotEmpty() -> this@LazyColumn.items(
                    items = uiState.menu.allItems,
                    key = { uiState -> uiState.objectId },
                ) { menu ->
                    SearchItem(
                        title = menu.title,
                        description = menu.description,
                        price = menu.price,
                        objectId = menu.objectId,
                        category = menu.categoryId,
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
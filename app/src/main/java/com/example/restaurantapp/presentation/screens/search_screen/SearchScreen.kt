package com.example.restaurantapp.presentation.screens.search_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.SearchItem
import com.example.restaurantapp.presentation.components.animations.SpacerHeight
import com.example.restaurantapp.presentation.components.animations.SpacerWidth
import com.example.restaurantapp.presentation.screens.common.LoadingScreen
import com.example.restaurantapp.presentation.theme.ExtraMediumSpacing
import com.example.restaurantapp.presentation.theme.LargeSpacing
import com.example.restaurantapp.presentation.theme.MediumSpacing
import com.example.restaurantapp.presentation.theme.SearchColor

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    navigateToDetailScreen: (String, String) -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = ExtraMediumSpacing)
            .padding(top = LargeSpacing)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = MediumSpacing),
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = null
                )
            }
            SpacerWidth(ExtraMediumSpacing)
            Text(
                text = stringResource(id = R.string.search),
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
            )
        }
        SpacerHeight(ExtraMediumSpacing)
        OutlinedTextField(
            value = uiState.query,
            onValueChange = onValueChange,
            trailingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
                disabledTextColor = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
                backgroundColor = SearchColor,
                leadingIconColor = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
                disabledLeadingIconColor = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
            ),
            placeholder = {
                androidx.compose.material3.Text(
                    modifier = modifier.padding(top = 4.dp),
                    text = stringResource(id = R.string.start_search),
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    ),
                    textAlign = TextAlign.Center
                )
            },
            minLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(LargeSpacing))
        )
        SpacerHeight(size = 32.dp)
        when {
            uiState.menu.allDesserts.isEmpty() -> {}
            uiState.isLoading -> LoadingScreen()
            else -> LazyColumn {
                items(
                    items = uiState.menu.allDesserts
                ) { menu ->
                    SearchItem(
                        title = menu.title,
                        description = menu.description,
                        price = menu.price,
                        objectId = menu.objectId,
                        category = menu.category_id,
                        gram = menu.gram,
                        image = menu.image,
                        rating = menu.rating.toString(),
                        navigateToDetailScreen = navigateToDetailScreen
                    )
                }
            }
        }
    }
}
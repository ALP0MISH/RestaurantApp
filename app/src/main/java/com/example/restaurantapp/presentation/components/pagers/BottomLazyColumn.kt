package com.example.restaurantapp.presentation.components.pagers

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.animations.SpacerWidth
import com.example.restaurantapp.presentation.models.MenuUi
import com.example.restaurantapp.presentation.theme.BackgroundModal
import com.example.restaurantapp.presentation.theme.BackgroundModalDar
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.DarkPlaceholder
import com.example.restaurantapp.presentation.theme.ExtraLargeSpacing
import com.example.restaurantapp.presentation.theme.ExtraMediumSpacing
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType")
@Composable
fun BottomLazyColumn(
    menu: List<MenuUi>,
    addToBasket: (MenuUi) -> Unit,
    navigateToBasketScreen: () -> Unit,
    navigateToDetailScreen: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier.padding(start = 8.dp),
        contentPadding = PaddingValues(12.dp),
    ) {
        item {
            Text(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .padding(top = ExtraMediumSpacing),
                text = stringResource(id = R.string.combination),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        items(
            items = menu,
            key = { list -> list.objectId }
        ) { list ->
            LazyColumnItem(
                menu = list,
                navigateToDetailScreen = navigateToDetailScreen,
                modifier = Modifier.padding(top = ExtraLargeSpacing),
                addToBasket = addToBasket,
                navigateToBasketScreen = navigateToBasketScreen
            )
        }
    }
}

@Composable
fun LazyColumnItem(
    menu: MenuUi,
    navigateToBasketScreen: () -> Unit,
    addToBasket: (MenuUi) -> Unit,
    navigateToDetailScreen: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isButtonCounterVisible by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                navigateToDetailScreen(
                    menu.objectId,
                    menu.categoryId,
                )
            }
            .clip(RoundedCornerShape(15.dp))
    ) {
        AsyncImage(
            modifier = Modifier
                .size(115.dp)
                .background(DarkPlaceholder),
            model = menu.imageUrl, contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(115.dp)
                .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    text = menu.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(16.dp),
                    imageVector = Icons.Default.Scale,
                    contentDescription = null,
                    tint = Color.Gray
                )
                SpacerWidth(2.dp)
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = menu.gram,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = menu.price,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary)
                        .clickable {
                            isButtonCounterVisible = !isButtonCounterVisible
                            addToBasket(menu)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (!isButtonCounterVisible) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                    if (isButtonCounterVisible) {
                        Icon(
                            imageVector = Icons.Default.ArrowRightAlt,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    navigateToBasketScreen()
                                },
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}

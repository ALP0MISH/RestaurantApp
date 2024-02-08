package com.example.restaurantapp.presentation.components.pagers

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantapp.presentation.components.animations.SpacerWidth
import com.example.restaurantapp.presentation.models.MenuUi
import com.example.restaurantapp.presentation.theme.BackgroundModal
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.DarkPlaceholder
import com.google.accompanist.pager.HorizontalPagerIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerWithIndicator(
    menu: List<MenuUi>,
    navigateToDetailScreen: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { menu.size }
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        HorizontalPager(
            modifier = Modifier.padding(start = 8.dp),
            state = pagerState,
        ) { page ->
            IncludeHorizontalPagerWithIndicator(
                menu = menu[page],
                navigateToDetailScreen = navigateToDetailScreen,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = menu.size,
            activeColor = Color.White,
            inactiveColor = Color.White.copy(alpha = 0.5f),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun HorizontalPagerItem(
    menu: MenuUi,
    navigateToDetailScreen: (String, String) -> Unit,
    addToBasket: (MenuUi) -> Unit,
    modifier: Modifier = Modifier
) {
    var isButtonCounterVisible by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .height(265.dp)
            .width(165.dp)
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                navigateToDetailScreen(
                    menu.objectId,
                    menu.categoryId,
                )
            },
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal)

                .align(Alignment.BottomCenter)
        ) {
            AsyncImage(
                model = menu.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkPlaceholder)
                    .height(165.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                text = menu.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(10.dp),
                    imageVector = Icons.Default.Scale,
                    contentDescription = null,
                    tint = Color.LightGray
                )
                SpacerWidth(4.dp)
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = menu.gram,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = menu.price,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .width(70.dp)
                        .clip(CircleShape)
                        .clickable {
                            isButtonCounterVisible = !isButtonCounterVisible
                            addToBasket(menu)
                        }
                        .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary),

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
                        ButtonCounter(
                            modifier = Modifier.clickable {
                                addToBasket(menu)
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Composable
fun IncludeHorizontalPagerWithIndicator(
    menu: MenuUi,
    navigateToDetailScreen: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(165.dp)
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                navigateToDetailScreen(
                    menu.objectId,
                    menu.categoryId,
                )
            }
    ) {
        AsyncImage(
            model = menu.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal)
        )
    }
}

@Preview
@Composable
fun IncludeHorizontalPagerWithIndicatorPreview() {
    MaterialTheme {
        HorizontalPagerItem(
            menu = MenuUi.unknown,
            navigateToDetailScreen = { param1, param2 -> },
            addToBasket = {},
        )
    }
}
package com.example.restaurantapp.presentation.components.pagers

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.presentation.models.MenuUi
import com.example.restaurantapp.presentation.screens.take_away_screen.TakeAwayUiState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable

fun HorizontalPagerContent(
    scrollState: ScrollState,
    uiState: TakeAwayUiState.Loaded,
    addToBasket: (MenuUi) -> Unit,
    navigateToBasketScreen: () -> Unit,
    navigateToDetailScreen: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {

    val menuList = listOf(
        uiState.hotDishes, uiState.fastFoot, uiState.desserts, uiState.salads, uiState.drinks
    )
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { menuList.size }
    val defaultIndicator = @Composable { tabPositions: List<TabPosition> ->
        TabRowDefaults.Indicator(
            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions), color = Color.Red
        )
    }

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = defaultIndicator,
        modifier = Modifier,
        backgroundColor = Color.Transparent
    ) {
        menuList.forEachIndexed { index, _ ->
            val header = getPagerHeaderByPosition(index)
            Tab(
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Transparent,
                text = {
                    Text(
                        text = header,
                        style = typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                    )

                },
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
    HorizontalPager(
        state = pagerState, modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .nestedScroll(
                remember {
                    object : NestedScrollConnection {
                        override fun onPreScroll(
                            available: Offset, source: NestedScrollSource
                        ): Offset {
                            return if (available.y > 0) Offset.Zero else Offset(
                                x = 0f, y = -scrollState.dispatchRawDelta(-available.y)
                            )
                        }
                    }
                },
            )
    ) { position ->
        val currentMenu = menuList[position]
        LazyRow(
            modifier = Modifier,
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalAlignment = Alignment.Top
        ) {
            items(items = currentMenu, key = { it.objectId }) { menuDomain ->
                HorizontalPagerItem(
                    menu = menuDomain,
                    navigateToDetailScreen = navigateToDetailScreen,
                    addToBasket = addToBasket,
                    navigateToBasketScreen = navigateToBasketScreen
                )
            }
        }
    }
}

@Composable
fun getPagerHeaderByPosition(position: Int): String = when (position) {
    0 -> "Hot Dishes"
    1 -> "Fast Foot"
    2 -> "Desserts"
    3 -> "Salads"
    else -> "Drinks"
}
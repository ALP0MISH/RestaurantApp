package com.example.restaurantapp.presentation.screens.take_away_screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.IncludeTopTakeScreen
import com.example.restaurantapp.presentation.components.animations.LottieErrorScreen
import com.example.restaurantapp.presentation.components.animations.ShimmerList
import com.example.restaurantapp.presentation.components.animations.SpacerHeight
import com.example.restaurantapp.presentation.components.pagers.BottomLazyColumn
import com.example.restaurantapp.presentation.components.pagers.HorizontalPagerContent
import com.example.restaurantapp.presentation.components.pagers.HorizontalPagerWithIndicator
import com.example.restaurantapp.presentation.models.MenuUi
import com.example.restaurantapp.presentation.theme.BackgroundModal
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.DarkPlaceholder
import com.example.restaurantapp.presentation.theme.ExtraLargeSpacing
import kotlinx.coroutines.flow.StateFlow

@Composable
fun TakeAwayScreen(
    uiStateFlow: StateFlow<TakeAwayUiState>,
    navigateToSearchScreen: () -> Unit,
    addToBasket: (MenuUi) -> Unit,
    navigateToEditScreen: () -> Unit,
    navigateToBasketScreen: () -> Unit,
    navigateToDetailScreen: (String, String) -> Unit,
    retryMenu: () -> Unit,
) {

    val theme: Boolean = isSystemInDarkTheme()
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor =
            if (theme) DarkPlaceholder.toArgb() else BackgroundSecondary.toArgb()
    }

    val fullScreenModifier = Modifier
        .background(if (isSystemInDarkTheme()) DarkPlaceholder else BackgroundModal)
        .fillMaxSize()

    val uiState by uiStateFlow.collectAsStateWithLifecycle()

    when (uiState) {
        is TakeAwayUiState.Error -> {
            LottieErrorScreen(
                errorMassage = (uiState as TakeAwayUiState.Error).message,
                refreshClick = retryMenu,
                modifier = fullScreenModifier
            )
        }

        is TakeAwayUiState.Loading -> {
            ShimmerList(modifier = fullScreenModifier)
        }

        is TakeAwayUiState.Loaded -> {
            val loadedUiState = uiState as TakeAwayUiState.Loaded
            LoadedScreen(
                modifier = fullScreenModifier,
                uiState = loadedUiState,
                navigateToDetailScreen = navigateToDetailScreen,
                navigateToSearchScreen = navigateToSearchScreen,
                addToBasket = addToBasket,
                navigateToBasketScreen = navigateToBasketScreen,
                navigateToEditScreen = navigateToEditScreen
            )
        }
    }
}

@Composable
fun LoadedScreen(
    uiState: TakeAwayUiState.Loaded,
    addToBasket: (MenuUi) -> Unit,
    navigateToBasketScreen: () -> Unit,
    navigateToEditScreen: () -> Unit,
    navigateToDetailScreen: (String, String) -> Unit,
    navigateToSearchScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
    ) {
        val screenHeight = maxHeight
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(top = 24.dp)

                .fillMaxSize()
                .verticalScroll(state = scrollState)
        ) {
            IncludeTopTakeScreen(
                navigateToSearchScreen = navigateToSearchScreen,
                user = uiState.user,
                navigateToEditScreen = navigateToEditScreen
            )
            SpacerHeight(ExtraLargeSpacing)
            HorizontalPagerWithIndicator(
                menu = uiState.hotDishes,
                navigateToDetailScreen = navigateToDetailScreen
            )
            SpacerHeight(ExtraLargeSpacing)
            HorizontalPagerContent(
                scrollState = scrollState,
                navigateToDetailScreen = navigateToDetailScreen,
                uiState = uiState,
                addToBasket = addToBasket,
                navigateToBasketScreen = navigateToBasketScreen
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight)
            ) {
                BottomLazyColumn(
                    menu = uiState.hotDishes,
                    navigateToDetailScreen = navigateToDetailScreen,
                    addToBasket = addToBasket,
                    navigateToBasketScreen = navigateToBasketScreen
                )
            }
        }
    }
}

@Composable
fun ErrorScreen(
    errorMassage: String,
    retryMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorMassage,
            style = MaterialTheme.typography.titleLarge
        )
        SpacerHeight(24.dp)
        Button(onClick = retryMenu) {
            Text(
                text = stringResource(id = R.string.retry),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
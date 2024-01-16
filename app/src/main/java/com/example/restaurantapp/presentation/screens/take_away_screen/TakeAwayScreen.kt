package com.example.restaurantapp.presentation.screens.take_away_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.pagers.HorizontalPagerWithIndicator
import com.example.restaurantapp.presentation.components.pagers.HorizontalPagerContent
import com.example.restaurantapp.presentation.components.pagers.BottomLazyColumn
import com.example.restaurantapp.presentation.components.IncludeTopTakeScreen
import com.example.restaurantapp.presentation.components.animations.SpacerHeight
import com.example.restaurantapp.presentation.theme.Background
import com.example.restaurantapp.presentation.components.animations.ShimmerList
import com.example.restaurantapp.presentation.screens.detail_screen.ItemDetailType
import com.example.restaurantapp.presentation.theme.ExtraLarge
import com.example.restaurantapp.presentation.theme.ExtraLargeSpacing
import com.example.restaurantapp.presentation.theme.ExtraMediumSpacing
import com.example.restaurantapp.presentation.theme.LargeSpacing
import com.example.restaurantapp.presentation.theme.MediumSpacing
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun TakeAwayScreen(
    uiStateFlow: StateFlow<TakeAwayUiState>,
    navigateToSearchScreen: () -> Unit,
    navigateToDetailScreen: (String, String) -> Unit,
    retryMenu: () -> Unit,
) {

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(Background)
    }
    val fullScreenModifier = Modifier
        .background(Background)
        .fillMaxSize()
    when (val uiState = uiStateFlow.collectAsStateWithLifecycle().value) {
        is TakeAwayUiState.Loading -> {
            ShimmerList(
                modifier = fullScreenModifier
            )
        }

        is TakeAwayUiState.Loaded -> {
            LoadedScreen(
                modifier = fullScreenModifier,
                uiState = uiState,
                navigateToDetailScreen = navigateToDetailScreen,
                navigateToSearchScreen = navigateToSearchScreen
            )
        }

        is TakeAwayUiState.Error -> {
            ErrorScreen(
                errorMassage = uiState.message,
                retryMenu = retryMenu,
                modifier = fullScreenModifier
            )
        }
    }
}

@Composable
fun LoadedScreen(
    uiState: TakeAwayUiState.Loaded,
    navigateToDetailScreen: (String, String) -> Unit,
    navigateToSearchScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    androidx.compose.material.Text(
                        text = stringResource(id = R.string.item_added_to_basket),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White
                    )
                },
                icon = {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = "",
                        tint = Color.White
                    )
                },
                onClick = {
                    scope.launch {
                        val result = snackbarHostState
                            .showSnackbar(
                                message = "1 Item added to basket",
                                actionLabel = "text",
                                duration = SnackbarDuration.Indefinite
                            )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                /* Handle snackbar action performed */
                            }

                            SnackbarResult.Dismissed -> {
                                /* Handle snackbar dismissed */
                            }
                        }
                    }
                }
            )
        }
    ) { contentPadding ->
        BoxWithConstraints(
            modifier = modifier
                .fillMaxSize()
                .background(Background)
        ) {
            val screenHeight = maxHeight
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(contentPadding)
                    .fillMaxSize()
                    .verticalScroll(state = scrollState)
                    .background(Background)
            ) {
                IncludeTopTakeScreen(
                    navigateToSearchScreen = navigateToSearchScreen,
                    user = uiState.user
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
                    uiState = uiState
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight)
                ) {
                    BottomLazyColumn(
                        menu = uiState.hotDishes,
                        navigateToDetailScreen = navigateToDetailScreen
                    )
                }
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
        horizontalAlignment = Alignment.CenterHorizontally
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
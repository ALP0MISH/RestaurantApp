package com.example.restaurantapp.presentation.screens.detail_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.animations.LottieErrorScreen
import com.example.restaurantapp.presentation.components.animations.SpacerHeight
import com.example.restaurantapp.presentation.components.pagers.ButtonCounterDetail
import com.example.restaurantapp.presentation.components.pagers.ButtonDetail
import com.example.restaurantapp.presentation.models.MenuUi
import com.example.restaurantapp.presentation.screens.common.ErrorScreen
import com.example.restaurantapp.presentation.screens.common.LoadingScreen
import com.example.restaurantapp.presentation.screens.take_away_screen.TakeAwayUiState
import com.example.restaurantapp.presentation.theme.Background
import com.example.restaurantapp.presentation.theme.BackgroundModal
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.ButtonColor
import com.example.restaurantapp.presentation.theme.DarkPlaceholder
import com.example.restaurantapp.presentation.theme.ExtraLargeSpacing
import com.example.restaurantapp.presentation.theme.ExtraMediumSpacing
import com.example.restaurantapp.presentation.theme.MediumSpacing
import com.example.restaurantapp.presentation.theme.SearchColor
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DetailScreen(
    uiStateFlow: StateFlow<DetailsUiState>,
    getFoodById: () -> Unit,
    addToBasket: (MenuUi) -> Unit,
    navigateToBasketScreen: () -> Unit,
    refreshClick: () -> Unit,
) {

    val fullScreenModifier = Modifier
        .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary)
        .fillMaxSize()

    LaunchedEffect(key1 = Unit) { getFoodById() }
    val uiState by uiStateFlow.collectAsStateWithLifecycle()


    when (uiState) {
        is DetailsUiState.Loading -> {
            LoadingScreen(modifier = fullScreenModifier)
        }

        is DetailsUiState.Error -> {
            LottieErrorScreen(
                errorMassage = (uiState as DetailsUiState.Error).message,
                refreshClick = refreshClick,
                modifier = fullScreenModifier
            )
        }

        is DetailsUiState.Loaded -> {
            val loadedUiState = uiState as DetailsUiState.Loaded
            LoadedScreen(
                menu = loadedUiState.menuUi,
                navigateToBasketScreen = navigateToBasketScreen,
                addToBasket = addToBasket
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoadedScreen(
    menu: MenuUi,
    addToBasket: (MenuUi) -> Unit,
    navigateToBasketScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isButtonCounterVisible by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        AsyncImage(
            model = menu.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxHeight()
        )
        IconButton(modifier = Modifier
            .size(32.dp)
            .align(Alignment.TopStart)
            .padding(start = 24.dp, top = 30.dp), onClick = { }) {
            Icon(
                modifier = Modifier.fillMaxWidth(),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(
                    if (isSystemInDarkTheme()) Background.copy(alpha = 0.9f)
                    else BackgroundModal.copy(alpha = 0.9f)

                )
                .align(Alignment.BottomCenter), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = menu.title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Row(
                modifier = Modifier, verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color.Yellow
                )
                Text(
                    text = menu.rating,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = menu.price,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = menu.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            SpacerHeight(size = 24.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .background(if (isSystemInDarkTheme()) DarkPlaceholder else BackgroundModal),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (!isButtonCounterVisible) {
                    Box(modifier = Modifier
                        .height(60.dp)
                        .width(287.dp)
                        .clip(CircleShape)
                        .clickable {
                            isButtonCounterVisible = !isButtonCounterVisible
                            addToBasket(menu)
                        }
                        .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary),
                        contentAlignment = Alignment.Center) {

                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
                if (isButtonCounterVisible) {
                    TextButton(
                        modifier = modifier
                            .height(60.dp)
                            .width(287.dp)
                            .clip(CircleShape),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary
                        ),
                        onClick = { navigateToBasketScreen() },
                    ) {
                        Text(
                            text = "Next",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                }
            }
        }
    }
}
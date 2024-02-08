package com.example.restaurantapp.presentation.screens.detail_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.animations.SpacerHeight
import com.example.restaurantapp.presentation.components.pagers.ButtonCounterDetail
import com.example.restaurantapp.presentation.components.pagers.ButtonDetail
import com.example.restaurantapp.presentation.models.MenuUi
import com.example.restaurantapp.presentation.screens.common.ErrorScreen
import com.example.restaurantapp.presentation.screens.common.LoadingScreen
import com.example.restaurantapp.presentation.theme.Background
import com.example.restaurantapp.presentation.theme.ButtonColor
import com.example.restaurantapp.presentation.theme.ExtraLargeSpacing
import com.example.restaurantapp.presentation.theme.SearchColor
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DetailScreen(
    uiStateFlow: StateFlow<DetailsUiState>,
    getFoodById: () -> Unit,
) {
    val fullScreenModifier = Modifier
        .background(Background)
        .fillMaxSize()

    LaunchedEffect(key1 = Unit) { getFoodById() }

    when (val uiState = uiStateFlow.collectAsStateWithLifecycle().value) {
        is DetailsUiState.Loading -> {
            LoadingScreen(modifier = fullScreenModifier)
        }

        is DetailsUiState.Error -> {
            ErrorScreen(
                message = uiState.message, onClick = { },
            )
        }

        is DetailsUiState.Loaded -> {
            LoadedScreen(menu = uiState.menuUi)
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoadedScreen(
    menu: MenuUi,
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
                tint = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Background.copy(alpha = 0.9f))
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = menu.title,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
            )
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color.Yellow
                )
                Text(
                    text = menu.rating.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = menu.price,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = menu.description,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            SpacerHeight(size = 24.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .background(SearchColor),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(modifier = Modifier
                    .height(60.dp)
                    .width(287.dp)
                    .clip(CircleShape)
                    .clickable {
                        isButtonCounterVisible = !isButtonCounterVisible
                    }
                    .background(ButtonColor), contentAlignment = Alignment.Center) {
                    if (!isButtonCounterVisible) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    }
                    if (isButtonCounterVisible) {
                        ButtonCounterDetail()
                    }
                }
            }
        }
    }
}

@Composable
fun LoadedScreenSecond(
    menu: MenuUi,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        AsyncImage(
            model = menu.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Background.copy(alpha = 0.9f))
                .align(Alignment.BottomCenter),
        ) {
            SpacerHeight(ExtraLargeSpacing)
            Row(
                modifier = Modifier.padding(horizontal = ExtraLargeSpacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = menu.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(modifier = Modifier
                    .size(22.dp)
                    .padding(bottom = 25.dp), onClick = { }) {
                    Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = null)
                }
            }
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = ExtraLargeSpacing),
                text = menu.gram,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
            )
            SpacerHeight(ExtraLargeSpacing)
            Row(
                modifier = Modifier, verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonDetail(modifier = Modifier.padding(start = ExtraLargeSpacing))
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(ExtraLargeSpacing),
                    text = menu.price,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = menu.description,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            SpacerHeight(size = 24.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .width(287.dp)
                        .clip(CircleShape)
                        .background(ButtonColor), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.add_to_card),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoadedScreenSecondPreview() {
    MaterialTheme {
        LoadedScreenSecond(
            menu = MenuUi.unknown
        )
    }
}
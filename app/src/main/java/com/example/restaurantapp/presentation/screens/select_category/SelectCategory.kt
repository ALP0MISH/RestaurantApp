package com.example.restaurantapp.presentation.screens.select_category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.animations.SpacerWidth
import com.example.restaurantapp.presentation.theme.Background
import com.example.restaurantapp.presentation.theme.DarkGrey

@Composable
fun SelectCategory(
    isTableScreen: () -> Unit,
    isTakeAwayScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 42.dp),
            text = stringResource(id = R.string.your_preference),
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        Row(
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(161.dp)
                    .clickable { isTakeAwayScreen() }
                    .clip(RoundedCornerShape(15.dp)),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.take_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .align(Alignment.BottomCenter)
                        .background(DarkGrey),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.take_away),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }
            SpacerWidth(32.dp)
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(170.dp)
                    .clickable { isTableScreen() }
                    .clip(RoundedCornerShape(15.dp)),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.table_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .align(Alignment.BottomCenter)
                        .background(DarkGrey),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.reserve_table),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectCategoryPreview() {
    MaterialTheme {
        SelectCategory(
            isTakeAwayScreen = {},
            isTableScreen = {}
        )
    }
}
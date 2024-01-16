package com.example.restaurantapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.animations.SpacerHeight
import com.example.restaurantapp.presentation.theme.ExtraMediumSpacing
import com.example.restaurantapp.presentation.theme.MediumElevation
import com.example.restaurantapp.presentation.theme.MediumSpacing
import com.example.restaurantapp.presentation.theme.SearchItemColor

@Composable
fun SearchItem(
    image: String,
    title: String,
    rating: String,
    gram: String,
    price: String,
    description: String,
    objectId: String,
    category: String,
    navigateToDetailScreen: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(325.dp)
            .height(189.dp)
            .clickable { navigateToDetailScreen(objectId, category) }
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(96.dp)
                    .height(76.dp)
                    .clip(RoundedCornerShape(ExtraMediumSpacing))
            )
            Column(
                modifier = Modifier.padding(horizontal = MediumElevation),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
                )
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = rating,
                        style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
                        color = SearchItemColor
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                }
                Text(
                    text = gram,
                    style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
                    color = SearchItemColor
                )
            }
        }
        SpacerHeight(MediumSpacing)
        Text(
            text = description,
            style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
            color = SearchItemColor,
            maxLines = 1
        )
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.amount),
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = price,
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
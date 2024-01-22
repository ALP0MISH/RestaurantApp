package com.example.restaurantapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.animations.SpacerHeight
import com.example.restaurantapp.presentation.theme.BackgroundModal
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.ExtraLargeSpacing
import com.example.restaurantapp.presentation.theme.ExtraMediumSpacing
import com.example.restaurantapp.presentation.theme.LargeSpacing
import com.example.restaurantapp.presentation.theme.MediumSpacing
import com.example.restaurantapp.presentation.theme.SmallSpacing

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
    Spacer(modifier = Modifier.height(MediumSpacing))
    Column(modifier = modifier
        .fillMaxWidth()
        .height(189.dp)
        .padding(8.dp)
        .padding(horizontal = LargeSpacing)
        .clip(RoundedCornerShape(15.dp))
        .clickable { navigateToDetailScreen(objectId, category) }
        .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(106.dp)
                    .height(96.dp)
                    .padding(MediumSpacing)
                    .clip(RoundedCornerShape(LargeSpacing))
            )
            Column(
                modifier = Modifier,
            ) {
                Text(
                    modifier = Modifier.padding(start = SmallSpacing),
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1
                )
                Row(
                    modifier = Modifier.padding(start = SmallSpacing),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier,
                        text = rating,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Icon(
                        modifier = Modifier.size(ExtraMediumSpacing),
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
                    )
                }
                Text(
                    modifier = Modifier.padding(start = SmallSpacing),
                    text = gram,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        SpacerHeight(MediumSpacing)
        Text(
            modifier = Modifier.padding(start = MediumSpacing),
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        SpacerHeight(ExtraMediumSpacing)
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                modifier = Modifier.padding(start = MediumSpacing),
                text = stringResource(id = R.string.amount),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                modifier = Modifier.padding(end = MediumSpacing),
                text = price,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun IsVisibleItem(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.height(ExtraLargeSpacing))
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = ExtraMediumSpacing)
            .padding(top = ExtraLargeSpacing),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.search_image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        SpacerHeight(MediumSpacing)
        Text(
            text = stringResource(id = R.string.sad_noresunt),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        SpacerHeight(ExtraMediumSpacing)
        Text(
            text = stringResource(id = R.string.serch_title),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}
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
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.DarkPlaceholder
import com.example.restaurantapp.presentation.theme.ExtraLargeSpacing
import com.example.restaurantapp.presentation.theme.ExtraMediumSpacing
import com.example.restaurantapp.presentation.theme.SearchColor
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType")
@Composable
fun BottomLazyColumn(
    menu: List<MenuUi>,
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
                color = Color.White
            )
        }
        items(
            items = menu,
            key = { list -> list.objectId }
        ) { list ->
            LazyColumnItem(
                image = list.image,
                objectId = list.objectId,
                title = list.title,
                gram = list.gram,
                price = list.price,
                navigateToDetailScreen = navigateToDetailScreen,
                categoryId = list.category_id,
                modifier = Modifier.padding(top = ExtraLargeSpacing),
            )
        }
    }
}

@Composable
fun LazyColumnItem(
    objectId: String,
    categoryId: String,
    image: String,
    title: String,
    gram: String,
    price: String,
    navigateToDetailScreen: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                navigateToDetailScreen(
                    objectId,
                    categoryId,
                )
            }
            .clip(RoundedCornerShape(15.dp))
    ) {
        AsyncImage(
            modifier = Modifier
                .size(115.dp)
                .background(DarkPlaceholder),
            model = image, contentDescription = null,
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
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
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
                    text = gram,
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
                    text = price,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(BackgroundModalDar)
                        .clickable {
                            scope.launch {
                                val result = snackbarHostState
                                    .showSnackbar(
                                        message = "Snackbar",
                                        actionLabel = "Action",
                                        // Defaults to SnackbarDuration.Short
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
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

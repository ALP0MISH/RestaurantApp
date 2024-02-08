package com.example.restaurantapp.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.animations.SpacerHeight
import com.example.restaurantapp.presentation.components.animations.SpacerWidth
import com.example.restaurantapp.presentation.models.User
import com.example.restaurantapp.presentation.theme.DarkPlaceholder

@Composable
fun IncludeTopTakeScreen(
    navigateToSearchScreen: () -> Unit,
    user: User,
    modifier: Modifier = Modifier
) {
    Log.i("iii", user.userName)
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(DarkPlaceholder),
                model = user.userAvatar,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            SpacerWidth(12.dp)
            Column {
                Text(
                    text = stringResource(id = R.string.reserve),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "${user.userName} ${user.userLastname}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.size(22.dp),
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        val (value, onValueChange) = remember { mutableStateOf("") }
        SpacerHeight(24.dp)
        SearchTextField(
            value = value,
            onValueChange = onValueChange,
            navigateToSearchScreen = { navigateToSearchScreen() }
        )
    }
}

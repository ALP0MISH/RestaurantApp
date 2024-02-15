package com.example.restaurantapp.presentation.navigations

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.restaurantapp.presentation.theme.BackgroundModal
import com.example.restaurantapp.presentation.theme.DarkPlaceholder
import com.example.restaurantapp.presentation.theme.LightBlue

enum class BottomTabs(
    val icon: ImageVector, val title: String, val route: String
) {
    Home(
        icon = Icons.Default.Home, title = "Home", route = "home_screen"
    ),
    SEARCH(
        icon = Icons.Default.Search, title = "Search", route = "search_screen"
    ),
    SHOPPING(
        icon = Icons.Default.ShoppingCart, title = "Shopping", route = "shopping_screen"
    ),
    SETTINGS(
        icon = Icons.Default.Settings, title = "Notification", route = "settings_screen"
    ),
}


@Composable
fun AppBottomNavigation(
    navController: NavController,
    items: List<BottomTabs>,
    currentRoute: String,
) {
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colorScheme.surface
    ) {
        items.forEach { bottomTabs ->
            AppBottomNavigationItem(
                modifier = Modifier.weight(1f),
                selected = currentRoute == bottomTabs.route,
                onClick = {
                    navController.navigate(bottomTabs.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                icon = bottomTabs.icon
            )
        }
    }
}

private const val DEFAULT_ICON_SIZE = 56

@Composable
fun AppBottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    iconSize: Dp = DEFAULT_ICON_SIZE.dp,
) {
    val scale = if (selected) 1.5f else 1.0f

    val color = if (selected) LightBlue
    else Color.LightGray

    val animatedScale: Float by animateFloatAsState(
        targetValue = scale, animationSpec = TweenSpec(
            durationMillis = 500, easing = FastOutSlowInEasing
        ), label = ""
    )
    val animatedColor by animateColorAsState(
        targetValue = color, animationSpec = TweenSpec(
            durationMillis = 500, easing = FastOutSlowInEasing
        ), label = ""
    )

    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(iconSize)
            .background(if (isSystemInDarkTheme()) DarkPlaceholder else BackgroundModal)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = String(),
            tint = animatedColor,
            modifier = Modifier.scale(animatedScale)
        )
    }
}
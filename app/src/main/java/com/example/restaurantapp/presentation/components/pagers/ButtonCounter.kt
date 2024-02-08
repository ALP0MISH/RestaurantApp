package com.example.restaurantapp.presentation.components.pagers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.ButtonColor
import com.example.restaurantapp.presentation.theme.SearchColor


@Composable
private fun CounterButton(
    value: String,
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    onValueClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(90.dp)
            .height(30.dp)
    ) {
        ButtonContainer(
            onValueDecreaseClick = onValueDecreaseClick,
            onValueIncreaseClick = onValueIncreaseClick,
            onValueClearClick = onValueClearClick,
            modifier = Modifier
        )
        DraggableThumbButton(
            value = value,
            onClick = onValueIncreaseClick,
            modifier = Modifier
        )
    }
}

@Composable
private fun DraggableThumbButton(
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .shadow(8.dp, shape = CircleShape)
            .clickable { onClick() }
    ) {
        Text(
            modifier = Modifier,
            text = value,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
            fontSize = 12.sp
        )
    }
}

private const val ICON_BUTTON_ALPHA_INITIAL = 0.3f
private const val CONTAINER_BACKGROUND_ALPHA_INITIAL = 0.6f

@Composable
private fun ButtonContainer(
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    onValueClearClick: () -> Unit,
    modifier: Modifier = Modifier,
    clearButtonVisible: Boolean = false,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .clip(CircleShape)
            .background(if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary)
            .padding(horizontal = 8.dp)
    ) {
        // кнопка уменьшения
        IconControlButton(
            icon = Icons.Outlined.Remove,
            contentDescription = "Decrease count",
            onClick = onValueDecreaseClick,
            tintColor = Color.White.copy(alpha = ICON_BUTTON_ALPHA_INITIAL)
        )

        // кнопка сброса
        if (clearButtonVisible) {
            IconControlButton(
                icon = Icons.Outlined.Clear,
                contentDescription = "Clear count",
                onClick = onValueClearClick,
                tintColor = Color.White.copy(alpha = ICON_BUTTON_ALPHA_INITIAL)
            )
        }
        // кнопка увеличения
        IconControlButton(
            icon = Icons.Outlined.Add,
            contentDescription = "Increase count",
            onClick = onValueIncreaseClick,
            tintColor = Color.White.copy(alpha = ICON_BUTTON_ALPHA_INITIAL)
        )
    }
}

@Composable
private fun IconControlButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tintColor: Color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        IconButton(

            onClick = onClick,
            modifier = Modifier.size(20.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = tintColor,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ButtonCounter(
    modifier: Modifier = Modifier
) {
    var valueCounter by remember {
        mutableIntStateOf(1)
    }
    CounterButton(
        value = valueCounter.toString(),
        onValueIncreaseClick = {
            valueCounter += 1
        },
        onValueDecreaseClick = {
            valueCounter = maxOf(valueCounter - 1, 0)
        },
        onValueClearClick = {
            valueCounter = 1
        }
    )
}

@Preview
@Composable
private fun ButtonCiunterPreview() {
    MaterialTheme {
        ButtonCounter()
    }
}

@Composable
fun ButtonCounterDetail(
    modifier: Modifier = Modifier
) {
    var valueCounter by remember {
        mutableIntStateOf(1)
    }
    Row(
        modifier = modifier,

        ) {
        CounterButton(
            modifier = Modifier
                .width(100.dp)
                .height(45.dp),
            value = valueCounter.toString(),
            onValueIncreaseClick = {
                valueCounter += 1
            },
            onValueDecreaseClick = {
                valueCounter = maxOf(valueCounter - 1, 1)
            },
            onValueClearClick = {
                valueCounter = 1
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier
                .width(155.dp)
                .height(45.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(androidx.compose.material3.MaterialTheme.colorScheme.onBackground),
            onClick = { }
        ) {
            Text(
                text = stringResource(id = R.string.next),
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun ButtonDetail(
    modifier: Modifier = Modifier
) {
    var valueCounter by remember {
        mutableIntStateOf(1)
    }
    Row(
        modifier = modifier,

        ) {
        CounterButton(
            modifier = Modifier
                .width(100.dp)
                .height(45.dp),
            value = valueCounter.toString(),
            onValueIncreaseClick = {
                valueCounter += 1
            },
            onValueDecreaseClick = {
                valueCounter = maxOf(valueCounter - 1, 1)
            },
            onValueClearClick = {
                valueCounter = 1
            }
        )
    }
}
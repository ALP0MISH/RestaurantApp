package com.example.restaurantapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantapp.presentation.theme.BackgroundModal
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.SearchColor

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    navigateToSearchScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(42.dp)
            .clickable { navigateToSearchScreen() },
        value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontSize = 12.sp
        ),
        shape = RoundedCornerShape(16.dp),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = Color.White
            )
        },
        enabled = false,
        placeholder = {},
        colors = TextFieldDefaults.colors(
            focusedContainerColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal,
            unfocusedContainerColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal,
            disabledContainerColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal,
            cursorColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal,
            focusedIndicatorColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal,
            unfocusedIndicatorColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundModal
        ),
    )
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    MaterialTheme {
        SearchTextField(
            value = "",
            onValueChange = {},
            navigateToSearchScreen = {},
        )
    }
}
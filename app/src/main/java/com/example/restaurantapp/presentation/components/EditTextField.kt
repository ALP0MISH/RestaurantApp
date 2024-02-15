package com.example.restaurantapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.presentation.components.animations.SpacerHeight
import com.example.restaurantapp.presentation.theme.Dark_Black
import com.example.restaurantapp.presentation.theme.MediumSpacing

@Composable
fun EditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isSingleLine: Boolean = true,
    topText: String,
    hint: String,
    fieldModifier: Modifier = Modifier,
    modifier: Modifier = Modifier
) {
    val grey = Dark_Black.copy(alpha = 0.5f)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = fieldModifier,
            text = topText.uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        SpacerHeight(MediumSpacing)
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = value,
            singleLine = isSingleLine,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledIndicatorColor = grey,
                unfocusedIndicatorColor = grey,
                focusedIndicatorColor = Blue
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold
            ),
            placeholder = {
                if (value.isEmpty()) Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            trailingIcon = {
                if (value.isNotEmpty()) Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onValueChange(String()) },
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        )
    }
}
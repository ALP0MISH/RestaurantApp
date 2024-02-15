package com.example.restaurantapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.theme.BackgroundModal
import com.example.restaurantapp.presentation.theme.BackgroundSecondary
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.ExtraLargeSpacing

@Composable
fun LoginTextField(
    onValueChange: (String) -> Unit,
    placeHolder: String,
    isPassword: Boolean = false,
    text: String,
    modifier: Modifier = Modifier
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
            .padding(horizontal = ExtraLargeSpacing)
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

            focusedContainerColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary,

            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            disabledTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,

            focusedPlaceholderColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary,
            disabledLabelColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary,
            unfocusedLabelColor = if (isSystemInDarkTheme()) BackgroundSecondaryDark else BackgroundSecondary,
        ),
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = if (isPassword) KeyboardType.Password
            else KeyboardType.Text
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation()
        else VisualTransformation.None,
        placeholder = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        trailingIcon = {
            if (isPassword) {
                val iconId = if (isPasswordVisible) R.drawable.hide_eye_icon_filled
                else R.drawable.show_eye_icon_filled
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = iconId),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    )
}
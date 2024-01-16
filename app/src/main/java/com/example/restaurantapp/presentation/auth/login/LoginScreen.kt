package com.example.restaurantapp.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.animations.LottieWorkingLoadingView
import com.example.restaurantapp.presentation.theme.Background
import com.example.restaurantapp.presentation.theme.BackgroundSecondaryDark
import com.example.restaurantapp.presentation.theme.ExtraLarge
import com.example.restaurantapp.presentation.theme.LargeSpacing
import com.example.restaurantapp.presentation.theme.MediumSpacing

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onEvent: (LoginEvent) -> Unit,

    ) {
    Scaffold { paddingValues ->
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var password by remember { mutableStateOf(TextFieldValue("")) }
        var hasError by remember { mutableStateOf(false) }
        var passwordVisualTransformation by remember {
            mutableStateOf<VisualTransformation>(PasswordVisualTransformation())
        }
        val passwordInteractionState = remember { MutableInteractionSource() }
        val emailInteractionState = remember { MutableInteractionSource() }

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(Background)
                .fillMaxSize()
                .padding(horizontal = LargeSpacing)
        ) {
            item { Spacer(modifier = Modifier.height(ExtraLarge)) }
            item { LottieWorkingLoadingView(context = LocalContext.current) }
            item {
                Text(
                    text = stringResource(id = R.string.welcombeck),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.padding(top = MediumSpacing),
                    color = Color.White
                )
            }
            item {

                OutlinedTextField(
                    value = uiState.email,
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = null)
                    },
                    maxLines = 1,
                    isError = hasError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.colors(
                        disabledIndicatorColor = Color.LightGray,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = Color.LightGray,

                        focusedContainerColor = BackgroundSecondaryDark,
                        disabledContainerColor = MaterialTheme.colorScheme.primary,
                        unfocusedContainerColor = BackgroundSecondaryDark,

                        focusedTextColor = Color.LightGray,
                        disabledTextColor = Color.LightGray,
                        unfocusedTextColor = Color.LightGray,

                        focusedPlaceholderColor = Color.LightGray,
                        disabledLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,

                        errorContainerColor = Color.Gray,
                        errorCursorColor = Color.Gray,
                        errorIndicatorColor = Color.Red,
                    ),
                    label = {
                        Text(
                            text = stringResource(id = R.string.email_addres),
                            color = Color.White
                        )
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.abcgmailcom),
                            color = Color.White
                        )
                    },
                    onValueChange = {
                        onEvent(LoginEvent.OnEmailChange(it))
                    },
                )
            }
            item {

                OutlinedTextField(
                    value = uiState.password,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Key,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.RemoveRedEye,
                            contentDescription = null,
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                            modifier = Modifier.clickable(
                                onClick = {
                                    passwordVisualTransformation =
                                        if (passwordVisualTransformation != VisualTransformation.None) {
                                            VisualTransformation.None
                                        } else {
                                            PasswordVisualTransformation()
                                        }
                                },
                            )
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        disabledIndicatorColor = Color.LightGray,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = Color.LightGray,

                        focusedContainerColor = BackgroundSecondaryDark,
                        disabledContainerColor = BackgroundSecondaryDark,
                        unfocusedContainerColor = BackgroundSecondaryDark,

                        focusedTextColor = Color.White,
                        disabledTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedPlaceholderColor = Color.LightGray,
                        disabledLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,

                        errorContainerColor = Color.Gray,
                        errorCursorColor = Color.Gray,
                        errorIndicatorColor = Color.Red,
                    ),
                    maxLines = 1,
                    isError = hasError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    label = {
                        androidx.compose.material3.Text(
                            text = stringResource(id = R.string.password),
                            color = Color.White
                        )
                    },
                    placeholder = {
                        androidx.compose.material3.Text(
                            text = stringResource(id = R.string.numbers),
                            color = Color.White
                        )
                    },
                    onValueChange = {
                        onEvent(LoginEvent.OnPasswordChange(it))
                    },
                    interactionSource = passwordInteractionState,
                    visualTransformation = passwordVisualTransformation,
                )
            }
            item {
                Button(
                    onClick = { onEvent(LoginEvent.OnLoginClick) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(50.dp)
                        .clip(CircleShape)
                ) {
                    Text(text = stringResource(id = R.string.login))
                }
            }
            item {
                Box(modifier = Modifier.padding(vertical = 16.dp)) {
                    Spacer(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )
                    Text(
                        text = stringResource(id = R.string.or_use),
                        color = Color.LightGray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(Background)
                            .padding(horizontal = 16.dp)
                    )
                }
            }
            item {
                OutlinedButton(
                    onClick = {}, modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Facebook,
                        contentDescription = null
                    )
                    androidx.compose.material3.Text(
                        text = stringResource(id = R.string.sign_in_with_facebook),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                OutlinedButton(
                    onClick = { }, modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.google_icon),
                        contentDescription = null,
                    )
                    Text(
                        text = stringResource(id = R.string.sign_in_with_google),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            item {
                val primaryColor = MaterialTheme.colorScheme.primary
                val annotatedString = remember {
                    AnnotatedString.Builder("Don't have an account? Register")
                        .apply {
                            addStyle(style = SpanStyle(color = primaryColor), 23, 31)
                        }
                }
                Text(
                    text = annotatedString.toAnnotatedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .clickable(onClick = { onEvent(LoginEvent.OnSingUpCLick) }),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            item { Spacer(modifier = Modifier.height(100.dp)) }
        }
    }
}

fun invalidInput(email: String, password: String) =
    email.isEmpty() || password.isEmpty()
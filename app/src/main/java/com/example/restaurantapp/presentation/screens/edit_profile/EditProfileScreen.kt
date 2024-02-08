package com.example.restaurantapp.presentation.screens.edit_profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantapp.R
import com.example.restaurantapp.domain.models.ChangeUserInfoDomain
import com.example.restaurantapp.presentation.components.EditTextField
import com.example.restaurantapp.presentation.components.animations.SpacerHeight
import com.example.restaurantapp.presentation.theme.DarkPlaceholder
import com.example.restaurantapp.presentation.theme.ExtraLargeSpacing
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EditProfileScreen(
    onEvent: (EditProfileEvent) -> Unit,
    uiState: StateFlow<EditProfileUiState>,
    changeUserInfo: (EditProfileUiState) -> Unit,
) {
    val fullScreenModifier = Modifier.fillMaxSize()
    when (val uiStateFlow = uiState.collectAsState().value) {
        else -> {
            LoadedContentScreen(
                modifier = fullScreenModifier,
                uiState = uiStateFlow,
                onEvent = onEvent,
                changeUserInfo = changeUserInfo
            )
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
private fun LoadedContentScreen(
    modifier: Modifier,
    uiState: EditProfileUiState,
    changeUserInfo: (EditProfileUiState) -> Unit,
    onEvent: (EditProfileEvent) -> Unit,
) {
    val isValidName =
        uiState.name.isNotEmpty() && uiState.name.first().isUpperCase()
    val isValidLastName =
        uiState.lastName.isNotEmpty() && uiState.lastName.first().isUpperCase()
    val isValidEmail = uiState.email.isNotEmpty() && uiState.email.endsWith("@gmail.com")
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var onEventState by remember { mutableStateOf(onEvent) }
        LaunchedEffect(onEvent) {
            onEventState = onEvent
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .size(26.dp)
                    .clip(CircleShape),
                onClick = {
                },
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            if (isValidName && isValidEmail && isValidLastName) {
                IconButton(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp)
                        .size(26.dp)
                        .clip(CircleShape),
                    onClick = {
                        changeUserInfo(uiState)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
        AsyncImage(
            model = uiState.image,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(DarkPlaceholder)
        )
        SpacerHeight(size = ExtraLargeSpacing)
        EditTextField(
            modifier = Modifier,
            value = uiState.name,
            onValueChange = {
                onEvent(EditProfileEvent.OnNameChange(it))
            },
            topText = stringResource(id = R.string.name),
            hint = stringResource(id = R.string.name_hilt)
        )
        SpacerHeight(size = ExtraLargeSpacing)
        EditTextField(
            modifier = Modifier,
            value = uiState.lastName,
            onValueChange = {
                onEvent(EditProfileEvent.OnLastNameChange(it))
            },
            topText = stringResource(id = R.string.last_name),
            hint = stringResource(id = R.string.your_lastname)
        )
        SpacerHeight(size = ExtraLargeSpacing)
        EditTextField(
            modifier = Modifier,
            value = uiState.email,
            onValueChange = {
                onEvent(EditProfileEvent.OnEmailChange(it))
            },
            topText = stringResource(id = R.string.email),
            hint = stringResource(id = R.string.email_hilt)
        )
        SpacerHeight(size = ExtraLargeSpacing)
        EditTextField(
            modifier = Modifier,
            value = uiState.password,
            onValueChange = {
                onEvent(EditProfileEvent.OnPasswordChange(it))
            },
            topText = stringResource(id = R.string.password),
            hint = stringResource(id = R.string.your_password)
        )
        SpacerHeight(size = ExtraLargeSpacing)
        EditTextField(
            modifier = Modifier,
            value = uiState.aboutMe,
            onValueChange = {
                onEvent(EditProfileEvent.OnAboutChange(it))
            },
            topText = stringResource(id = R.string.about_me),
            hint = stringResource(id = R.string.about_me_hint)
        )
    }
}
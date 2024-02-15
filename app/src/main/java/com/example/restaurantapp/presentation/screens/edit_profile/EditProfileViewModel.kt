package com.example.restaurantapp.presentation.screens.edit_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.models.ChangeUserInfoDomain
import com.example.restaurantapp.domain.use_cases.current_user.ChangeUserInfoUseCase
import com.example.restaurantapp.domain.use_cases.current_user.FetchCurrentUserUseCase
import com.example.restaurantapp.presentation.managers.toast.ShowToastUseCase
import com.example.restaurantapp.presentation.mapper.toUi
import com.example.socialapp.data.repositories.DEFAULT_ERROR_MESSAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val SUCCESS_MESSAGE = "User Successfully Updated"

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val changeUserInfoUseCase: ChangeUserInfoUseCase,
    private val currentUserUseCase: FetchCurrentUserUseCase,
    private val showToastUseCase: ShowToastUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState: StateFlow<EditProfileUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = currentUserUseCase.invoke()
            _uiState.value = EditProfileUiState(
                image = result.userAvatar ?: String(),
                name = result.userName,
                lastName = result.userLastname,
                email = result.email,
                aboutMe = result.historyId,
                password = result.password,
                objectId = result.objectId
            )
        }
    }

    fun onEvent(event: EditProfileEvent) {
        when (event) {
            is EditProfileEvent.OnAboutChange -> doAboutChanged(event.value)
            is EditProfileEvent.OnEmailChange -> doEmailChanged(event.value)
            is EditProfileEvent.OnNameChange -> doNameChanged(event.value)
            is EditProfileEvent.OnLastNameChange -> doLastNameChanged(event.value)
            is EditProfileEvent.OnPasswordChange -> doPasswordChanged(event.value)
            is EditProfileEvent.OnSaveButtonClick -> onChangeUserInfo(event)
        }
    }

    private fun onChangeUserInfo(event: EditProfileEvent.OnSaveButtonClick) {
        viewModelScope.launch {
            try {
                changeUserInfoUseCase.changeUserInfo(
                    menu = ChangeUserInfoDomain(
                        email = event.email,
                        password = event.password,
                        userLastname = event.lastName,
                        userName = event.firstName,
                        aboutMe = event.aboutMe
                    ),
                )
                showToastUseCase.showToast(SUCCESS_MESSAGE)
            } catch (e: Exception) {
                showToastUseCase.showToast(DEFAULT_ERROR_MESSAGE)
            }
        }
    }

    private fun doAboutChanged(value: String) {
        _uiState.update { uiState ->
            uiState.copy(aboutMe = value)
        }
    }

    private fun doEmailChanged(value: String) {
        _uiState.update { uiState ->
            uiState.copy(email = value)
        }
    }

    private fun doNameChanged(value: String) {
        _uiState.update { uiState ->
            uiState.copy(name = value)
        }
    }

    private fun doLastNameChanged(value: String) {
        _uiState.update { uiState ->
            uiState.copy(lastName = value)
        }
    }

    private fun doPasswordChanged(value: String) {
        _uiState.update { uiState ->
            uiState.copy(password = value)
        }
    }
}

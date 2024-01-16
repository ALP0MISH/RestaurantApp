package com.example.restaurantapp.presentation.auth.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.use_cases.current_user.SaveCurrentUserUseCase
import com.example.restaurantapp.domain.use_cases.signup.SignUpUseCase
import com.example.restaurantapp.presentation.extentions.createMutableSharedFlowAsSingleLiveEvent
import com.example.restaurantapp.presentation.managers.GlobalNavigatorManager
import com.example.restaurantapp.presentation.managers.toast.ShowToastUseCase
import com.example.restaurantapp.presentation.navigations.navGraph.MAIN_NAV_GRAPH_ROUTE
import com.example.socialapp.data.repositories.DEFAULT_ERROR_MESSAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val saveCurrentUserUseCase: SaveCurrentUserUseCase,
    private val navigatorManager: GlobalNavigatorManager,
    private val showToastUseCase: ShowToastUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _navControllerFlow = createMutableSharedFlowAsSingleLiveEvent<String>()
    val navControllerFlow: SharedFlow<String> = _navControllerFlow.asSharedFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.OnEmailChange -> doEmailChange(event)
            is SignUpEvent.OnNameChange -> doNameChange(event)
            is SignUpEvent.OnLastNameChange -> doLastNameChange(event)
            is SignUpEvent.OnPasswordChange -> doPasswordChange(event)
            is SignUpEvent.OnLoginClick -> onLoginClick()
            is SignUpEvent.OnSingUpCLick -> onSingUpCLick()
        }
    }

    private fun onSingUpCLick() {
        viewModelScope.launch {
            val result = signUpUseCase(
                name = uiState.value.name,
                lastName = uiState.value.lastName,
                email = uiState.value.email,
                password = uiState.value.password
            )
            when (result) {
                is Result.Error -> {
                    showToastUseCase.showToast(result.message ?: DEFAULT_ERROR_MESSAGE)
                    Log.e("SocialApp", "Error onSingUpCLick ${result.message ?: ""}")
                }

                is Result.Success -> {
                    val user = result.data ?: return@launch
                    saveCurrentUserUseCase(user)
                    navigatorManager.navigateTo(MAIN_NAV_GRAPH_ROUTE, true)
                    Log.e("SocialApp", "Success onSingUpCLick ${result.data}")
                }
            }
        }
    }

    private fun onLoginClick() {
        _navControllerFlow.tryEmit(SignUpDestination.route())
    }

    private fun doPasswordChange(event: SignUpEvent.OnPasswordChange) {
        _uiState.update { currentState ->
            currentState.copy(password = event.value)
        }
    }

    private fun doEmailChange(event: SignUpEvent.OnEmailChange) {
        _uiState.update { currentState ->
            currentState.copy(email = event.value)
        }
    }

    private fun doNameChange(event: SignUpEvent.OnNameChange) {
        _uiState.update { currentState ->
            currentState.copy(name = event.value)
        }
    }

    private fun doLastNameChange(event: SignUpEvent.OnLastNameChange) {
        _uiState.update { currentState ->
            currentState.copy(lastName = event.value)
        }
    }
}
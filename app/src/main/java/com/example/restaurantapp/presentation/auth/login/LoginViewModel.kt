package com.example.restaurantapp.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.UserDomain
import com.example.restaurantapp.domain.use_cases.current_user.SaveCurrentUserUseCase
import com.example.restaurantapp.domain.use_cases.signIn.SignInUseCase
import com.example.restaurantapp.presentation.auth.signup.SignUpDestination
import com.example.restaurantapp.presentation.extentions.createMutableSharedFlowAsSingleLiveEvent
import com.example.restaurantapp.presentation.managers.GlobalNavigatorManager
import com.example.restaurantapp.presentation.managers.toast.ShowToastUseCase
import com.example.restaurantapp.presentation.navigations.BottomTabs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val DEFAULT_ERROR_MESSAGE = "Something went wrong!"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val saveCurrentUserUseCase: SaveCurrentUserUseCase,
    private val navigatorManager: GlobalNavigatorManager,
    private val showToastUseCase: ShowToastUseCase

) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _navControllerFlow = createMutableSharedFlowAsSingleLiveEvent<String>()
    val navControllerFlow: SharedFlow<String> = _navControllerFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> doEmailChange(event)
            is LoginEvent.OnPasswordChange -> doPasswordChange(event)
            is LoginEvent.OnLoginClick -> onLoginClick()
            is LoginEvent.OnSingUpCLick -> onSingUpCLick()
        }
    }

    private fun onSingUpCLick() {
        _navControllerFlow.tryEmit(SignUpDestination.route())
    }

    private fun onLoginClick() {
        val email = _uiState.value.email
        val password = _uiState.value.password
        viewModelScope.launch {
            val result = signInUseCase(
                email = email,
                password = password
            )
            handleLoginResult(result)
        }
        _uiState.update { currentState ->
            currentState.copy(isAuthentication = true)
        }
    }

    private fun handleLoginResult(
        result: Result<UserDomain>
    ) {
        when (result) {
            is Result.Error -> {
                showToastUseCase.showToast(result.message ?: DEFAULT_ERROR_MESSAGE)
                Log.e("RestaurantApp", "message = ${result.message}")
            }

            is Result.Success -> {
                val user = result.data
                if (user == null) {
                    showToastUseCase.showToast(result.message ?: DEFAULT_ERROR_MESSAGE)
                    return
                }
                saveCurrentUserUseCase(user)
                navigatorManager.navigateTo(BottomTabs.Home.route)
                Log.e("RestaurantApp", "data = ${result.message}")
            }
        }
        _uiState.update { currentState ->
            currentState.copy(isSuccessesAuth = true)
        }
    }

    private fun doPasswordChange(event: LoginEvent.OnPasswordChange) {
        _uiState.update { currentState ->
            currentState.copy(password = event.value)
        }
    }

    private fun doEmailChange(event: LoginEvent.OnEmailChange) {
        _uiState.update { currentState ->
            currentState.copy(email = event.value)
        }
    }
}
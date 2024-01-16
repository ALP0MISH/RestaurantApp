package com.example.restaurantapp.presentation.auth.login

sealed class LoginEvent {
    data class OnEmailChange(val value: String) : LoginEvent()
    data class OnPasswordChange(val value: String) : LoginEvent()
    data object OnSingUpCLick: LoginEvent()
    data object OnLoginClick: LoginEvent()

}
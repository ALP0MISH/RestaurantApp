package com.example.restaurantapp.presentation.auth.signup

sealed class SignUpEvent {
    data class OnEmailChange(val value: String) : SignUpEvent()

    data class OnNameChange(val value: String) : SignUpEvent()

    data class OnLastNameChange(val value: String) : SignUpEvent()

    data class OnPasswordChange(val value: String) : SignUpEvent()

    data object OnSingUpCLick: SignUpEvent()

    data object OnLoginClick: SignUpEvent()
}
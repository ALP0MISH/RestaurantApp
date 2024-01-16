package com.example.restaurantapp.presentation.auth.login

data class LoginUiState(
    val email : String = String(),
    val password : String = String(),
    val errorMessage : String? = null,
    val isAuthentication: Boolean = false,
    val isSuccessesAuth: Boolean = false
)
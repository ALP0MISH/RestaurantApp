package com.example.restaurantapp.presentation.auth.signup

data class SignUpUiState(
    val name: String = String(),
    val lastName: String = String(),
    val email: String = String(),
    val password: String = String(),
    val errorMessage : String? = null,
    val isAuthentication: Boolean = false,
    val isSuccessesAuth: Boolean = false
)
package com.example.restaurantapp.presentation.screens.edit_profile

import androidx.compose.runtime.Immutable

@Immutable
data class EditProfileUiState(
    val email: String = String(),
    val lastName: String = String(),
    val name: String = String(),
    val image: String = String(),
    val password: String = String(),
    val aboutMe: String = String(),
    val objectId: String = String(),
)
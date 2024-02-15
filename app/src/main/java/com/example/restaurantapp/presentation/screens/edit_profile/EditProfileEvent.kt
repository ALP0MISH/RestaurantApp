package com.example.restaurantapp.presentation.screens.edit_profile

import androidx.compose.runtime.Immutable

@Immutable
sealed class EditProfileEvent {

    @Immutable
    data class OnSaveButtonClick(
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
        val aboutMe: String,
    ) : EditProfileEvent()

    data class OnEmailChange(val value: String) : EditProfileEvent()
    data class OnPasswordChange(val value: String) : EditProfileEvent()
    data class OnAboutChange(val value: String) : EditProfileEvent()
    data class OnLastNameChange(val value: String) : EditProfileEvent()
    data class OnNameChange(val value: String) : EditProfileEvent()
}
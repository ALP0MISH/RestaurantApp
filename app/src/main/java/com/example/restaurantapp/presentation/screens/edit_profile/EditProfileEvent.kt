package com.example.restaurantapp.presentation.screens.edit_profile

import android.graphics.Bitmap

sealed class EditProfileEvent {
    data object OnSaveButtonClick : EditProfileEvent()
    data class OnEmailChange(val value: String) : EditProfileEvent()
    data class OnPasswordChange(val value: String) : EditProfileEvent()
    data class OnAboutChange(val value: String) : EditProfileEvent()
    data class OnLastNameChange(val value: String) : EditProfileEvent()
    data class OnNameChange(val value: String) : EditProfileEvent()
}
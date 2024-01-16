package com.example.restaurantapp.presentation.screens.detail_screen

import com.example.restaurantapp.presentation.models.MenuUi
import javax.annotation.concurrent.Immutable

sealed class DetailsUiState {

    data object Loading : DetailsUiState()

    @Immutable
    data class Loaded(
        val menuUi: MenuUi,
    ) : DetailsUiState()

    data class Error(val message: String) : DetailsUiState()
}
package com.example.restaurantapp.presentation.screens.take_away_screen

import com.example.restaurantapp.presentation.models.CategoryUI
import com.example.restaurantapp.presentation.models.MenuUi
import com.example.restaurantapp.presentation.models.User
import javax.annotation.concurrent.Immutable

sealed class TakeAwayUiState {
    data object Loading : TakeAwayUiState()

    @Immutable
    data class Loaded(
        val drinks: List<MenuUi> = emptyList(),
        val desserts: List<MenuUi> = emptyList(),
        val hotDishes: List<MenuUi> = emptyList(),
        val fastFoot: List<MenuUi> = emptyList(),
        val salads: List<MenuUi> = emptyList(),
        val category: CategoryUI = CategoryUI.unknown,
        val user: User,
    ) : TakeAwayUiState()

    data class Error(val message: String) : TakeAwayUiState()
}
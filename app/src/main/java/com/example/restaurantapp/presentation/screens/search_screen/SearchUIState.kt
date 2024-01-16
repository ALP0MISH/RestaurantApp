package com.example.restaurantapp.presentation.screens.search_screen

data class SearchUiState(
    val query: String = String(),
    val menu: AllMenuUI = AllMenuUI(),
    val isLoading: Boolean = false
)
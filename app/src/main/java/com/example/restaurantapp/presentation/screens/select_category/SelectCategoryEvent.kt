package com.example.restaurantapp.presentation.screens.select_category

sealed class SelectCategoryEvent {
    data object OnTakeAwayClick:SelectCategoryEvent()
    data object OnSelectTableClick:SelectCategoryEvent()
}
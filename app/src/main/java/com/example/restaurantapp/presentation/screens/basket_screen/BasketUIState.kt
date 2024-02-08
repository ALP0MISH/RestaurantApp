package com.example.restaurantapp.presentation.screens.basket_screen

import com.example.restaurantapp.presentation.models.MenuUi
import javax.annotation.concurrent.Immutable

@Immutable
data class BasketUIState(
    val addToBasket: MenuUi = MenuUi.unknown,
    val fetchFromBasket: List<MenuUi> = emptyList(),
    val error: String = ""
)
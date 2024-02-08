package com.example.restaurantapp.domain.use_cases.basket

import com.example.restaurantapp.domain.models.BasketMenuDomain
import com.example.restaurantapp.domain.models.MenuDomain

interface AddToBasketUseCase {

    suspend fun addToBasket(menu: BasketMenuDomain)
}
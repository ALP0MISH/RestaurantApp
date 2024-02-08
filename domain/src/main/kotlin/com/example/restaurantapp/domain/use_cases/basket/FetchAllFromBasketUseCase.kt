package com.example.restaurantapp.domain.use_cases.basket

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.BasketMenuDomain
import com.example.restaurantapp.domain.models.MenuDomain

interface FetchAllFromBasketUseCase {
    suspend fun fetchFromBasket(): Result<List<MenuDomain>>
}
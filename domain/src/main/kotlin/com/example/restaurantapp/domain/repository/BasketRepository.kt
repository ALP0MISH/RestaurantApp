package com.example.restaurantapp.domain.repository

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.BasketMenuDomain
import com.example.restaurantapp.domain.models.MenuDomain

interface BasketRepository {
    suspend fun addToBasket(menu: BasketMenuDomain)
    suspend fun fetchFromBasket(): Result<List<MenuDomain>>
    suspend fun fetchBasketById(objectId: String): Result<MenuDomain>
}
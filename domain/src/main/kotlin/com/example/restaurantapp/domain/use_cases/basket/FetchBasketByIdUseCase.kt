package com.example.restaurantapp.domain.use_cases.basket

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.MenuDomain

interface FetchBasketByIdUseCase {
    suspend fun fetchBasketById(objectId: String): Result<MenuDomain>
}
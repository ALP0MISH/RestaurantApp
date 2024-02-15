package com.example.restaurantapp.domain.use_cases.basket

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.MenuDomain

interface FetchBasketByIdUseCase {
    suspend operator fun invoke(objectId: String): Result<MenuDomain>
}
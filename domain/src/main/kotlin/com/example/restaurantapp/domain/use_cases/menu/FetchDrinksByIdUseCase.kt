package com.example.restaurantapp.domain.use_cases.menu

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.MenuDomain

interface FetchDrinksByIdUseCase {

    suspend operator fun invoke(objectId: String): Result<MenuDomain>
}
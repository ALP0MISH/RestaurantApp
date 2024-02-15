package com.example.restaurantapp.domain.use_cases.basket

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.domain.repository.BasketRepository

class FetchBasketByIdUseCaseImpl(
    private val repository: BasketRepository
) : FetchBasketByIdUseCase {

    override suspend fun invoke(objectId: String): Result<MenuDomain> {
        return repository.fetchBasketById(objectId)
    }
}
package com.example.restaurantapp.domain.use_cases.basket

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.BasketMenuDomain
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.domain.repository.BasketRepository

class FetchAllFromBasketUseCaseImpl(
    private val repository: BasketRepository
) : FetchAllFromBasketUseCase {
    override suspend fun fetchFromBasket(): Result<List<MenuDomain>> {
        return repository.fetchFromBasket()
    }
}
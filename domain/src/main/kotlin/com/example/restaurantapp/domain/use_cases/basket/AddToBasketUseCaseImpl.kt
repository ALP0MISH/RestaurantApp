package com.example.restaurantapp.domain.use_cases.basket

import com.example.restaurantapp.domain.models.BasketMenuDomain
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.domain.repository.BasketRepository

class AddToBasketUseCaseImpl(
    private val repository: BasketRepository
) : AddToBasketUseCase {
    override suspend fun addToBasket(menu: BasketMenuDomain) {
        return repository.addToBasket(menu)
    }
}
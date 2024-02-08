package com.example.socialapp.data.cloud.source

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.socialapp.data.cloud.models.basket.BasketMenu
import com.example.socialapp.data.cloud.models.menu.MenuCloud

interface FetchFromBasketCloudDataSource {

    suspend fun fetchFromBasket(): List<MenuCloud>

    suspend fun addToBasket(menu: BasketMenu)

    suspend fun fetchBasketById(objectId: String): List<MenuCloud>
}
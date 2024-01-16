package com.example.restaurantapp.domain.use_cases.menu

import com.example.restaurantapp.domain.models.MenuDomain

interface FetchMenuByIdUserCase {

    suspend fun fetchSaladsById(objectId: String): MenuDomain

    suspend fun fetchDessertsById(objectId: String): MenuDomain

    suspend fun fetchFastFoodById(objectId: String): MenuDomain

    suspend fun fetchDrinksById(objectId: String): MenuDomain

    suspend fun fetchHotDishesByID(objectId: String): MenuDomain
}
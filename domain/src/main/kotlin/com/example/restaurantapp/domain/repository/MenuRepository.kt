package com.example.restaurantapp.domain.repository

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.CategoryDomain
import com.example.restaurantapp.domain.models.MenuDomain

interface MenuRepository {

    suspend fun getCategories(
        foodId: String
    ): Result<List<CategoryDomain>>

    suspend fun fetchAllDrinks(): Result<List<MenuDomain>>

    suspend fun fetchAllDesserts(): Result<List<MenuDomain>>

    suspend fun fetchAllFastFood(): Result<List<MenuDomain>>

    suspend fun fetchSaladsById(objectId: String): Result<MenuDomain>

    suspend fun fetchDessertsById(objectId: String): Result<MenuDomain>

    suspend fun fetchFastFoodById(objectId: String): Result<MenuDomain>

    suspend fun fetchDrinksById(objectId: String): Result<MenuDomain>

    suspend fun fetchHotDishesByID(objectId: String): Result<MenuDomain>

    suspend fun fetchAllHotDishes(): Result<List<MenuDomain>>

    suspend fun fetchAllSalads(): Result<List<MenuDomain>>

}
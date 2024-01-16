package com.example.socialapp.data.cloud.source

import com.example.socialapp.data.cloud.models.menu.CategoryCloud
import com.example.socialapp.data.cloud.models.menu.MenuCloud

interface MenuCloudDataSource {

    suspend fun fetchAllCategories(
        foodId: String,
    ): List<CategoryCloud>

    suspend fun fetchAllDrinks(): List<MenuCloud>

    suspend fun fetchAllDesserts(): List<MenuCloud>

    suspend fun fetchAllFastFood(): List<MenuCloud>

    suspend fun fetchAllHotDishes(): List<MenuCloud>

    suspend fun fetchAllSalads(): List<MenuCloud>
}
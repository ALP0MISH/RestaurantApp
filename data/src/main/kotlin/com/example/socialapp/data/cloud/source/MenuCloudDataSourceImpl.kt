package com.example.socialapp.data.cloud.source

import android.util.Log
import com.example.socialapp.data.cloud.models.menu.CategoryCloud
import com.example.socialapp.data.cloud.models.menu.MenuCloud
import com.example.socialapp.data.cloud.service.MenuService
import java.util.concurrent.CancellationException
import javax.inject.Inject

class MenuCloudDataSourceImpl @Inject constructor(
    private val menuService: MenuService
) : MenuCloudDataSource {

    override suspend fun fetchAllCategories(
        foodId: String
    ): List<CategoryCloud> {
        return try {
            val response = menuService.getCategories(
                foodId
            )
            if (response.isSuccessful) response.body()?.categories ?: emptyList()
            else emptyList()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            emptyList()
        }
    }

    override suspend fun fetchAllDrinks(): List<MenuCloud> {
        return try {
            val response = menuService.fetchAllDrinks()
            if (response.isSuccessful) response.body()?.results ?: emptyList()
            else emptyList()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            emptyList()
        }
    }

    override suspend fun fetchAllDesserts(): List<MenuCloud> {
        return try {
            val response = menuService.fetchAllDesserts()
            if (response.isSuccessful) response.body()?.results ?: emptyList()
            else emptyList()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            emptyList()
        }
    }

    override suspend fun fetchAllFastFood(): List<MenuCloud> {
        return try {
            val response = menuService.fetchAllFastFood()
            if (response.isSuccessful) response.body()?.results ?: emptyList()
            else emptyList()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            emptyList()
        }
    }

    override suspend fun fetchAllHotDishes(): List<MenuCloud> {
        return try {
            val result = menuService.fetchAllHotDishes()
            if (result.isSuccessful) result.body()?.results ?: emptyList()
            else emptyList()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            emptyList()
        }
    }

    override suspend fun fetchAllSalads(): List<MenuCloud> {
        return try {
            val response = menuService.fetchAllSalads()
            if (response.isSuccessful) response.body()?.results ?: emptyList()
            else emptyList()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            emptyList()
        }
    }
}


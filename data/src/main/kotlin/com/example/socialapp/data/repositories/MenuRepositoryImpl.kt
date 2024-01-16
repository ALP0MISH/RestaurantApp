package com.example.socialapp.data.repositories

import android.util.Log
import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.CategoryDomain
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.domain.repository.MenuRepository
import com.example.socialapp.data.cloud.models.menu.toDomain
import com.example.socialapp.data.cloud.service.MenuService
import com.example.socialapp.data.cloud.source.MenuCloudDataSource
import java.util.concurrent.CancellationException
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val menuCloudDataSource: MenuCloudDataSource,
    private val menuService: MenuService,
) : MenuRepository {

    override suspend fun getCategories(
        foodId: String
    ): Result<List<CategoryDomain>> {
        return try {
            val params = "{\"objectId\":\"$foodId\"}"
            val response = menuCloudDataSource.fetchAllCategories(
                params
            ).map { it.toDomain() }
            Result.Success(data = response)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun fetchAllDrinks(): Result<List<MenuDomain>> {
        return try {
            val response = menuCloudDataSource.fetchAllDrinks().map { it.toDomain() }
            Result.Success(data = response)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun fetchAllDesserts(): Result<List<MenuDomain>> {
        return try {
            val response = menuCloudDataSource.fetchAllDesserts().map { it.toDomain() }
            Result.Success(data = response)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun fetchAllFastFood(): Result<List<MenuDomain>> {
        return try {
            val response = menuCloudDataSource.fetchAllFastFood().map { it.toDomain() }
            Result.Success(data = response)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun fetchSaladsById(objectId: String): Result<MenuDomain> {
        return try {
            val params = "{\"objectId\":\"$objectId\"}"
            val response = menuService.fetchSaladsById(params)
            val user = response.body()?.results?.first()?.toDomain() ?: MenuDomain.unknown
            Result.Success(data = user)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun fetchDessertsById(objectId: String): Result<MenuDomain> {
        return try {
            val params = "{\"objectId\":\"$objectId\"}"
            val response = menuService.fetchDessertsById(params)
            val user = response.body()?.results?.first()?.toDomain() ?: MenuDomain.unknown
            Result.Success(data = user)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun fetchFastFoodById(objectId: String): Result<MenuDomain> {
        return try {
            val params = "{\"objectId\":\"$objectId\"}"
            val response = menuService.fetchFastFoodById(params)
            val user = response.body()?.results?.first()?.toDomain() ?: MenuDomain.unknown
            Result.Success(data = user)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun fetchDrinksById(objectId: String): Result<MenuDomain> {
        return try {
            val params = "{\"objectId\":\"$objectId\"}"
            val response = menuService.fetchDrinkById(params)
            val user = response.body()?.results?.first()?.toDomain() ?: MenuDomain.unknown
            Result.Success(data = user)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun fetchHotDishesByID(objectId: String): Result<MenuDomain> {
        return try {
            val params = "{\"objectId\":\"$objectId\"}"
            val response = menuService.fetchHotDishesById(params)
            val user = response.body()?.results?.first()?.toDomain() ?: MenuDomain.unknown
            Result.Success(data = user)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun fetchAllHotDishes(): Result<List<MenuDomain>> {
        return try {
            val response = menuCloudDataSource.fetchAllHotDishes().map { it.toDomain() }
            Result.Success(data = response)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun fetchAllSalads(): Result<List<MenuDomain>> {
        return try {
            val response = menuCloudDataSource.fetchAllSalads().map { it.toDomain() }
            Result.Success(data = response)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }
}
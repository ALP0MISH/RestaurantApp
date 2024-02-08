package com.example.socialapp.data.repositories

import android.util.Log
import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.BasketMenuDomain
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.domain.repository.BasketRepository
import com.example.socialapp.data.cloud.models.basket.toDomain
import com.example.socialapp.data.cloud.models.menu.toDomain
import com.example.socialapp.data.cloud.service.BasketService
import com.example.socialapp.data.cloud.source.FetchFromBasketCloudDataSource
import kotlinx.coroutines.delay
import java.util.concurrent.CancellationException
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val dataSource: FetchFromBasketCloudDataSource,
) : BasketRepository {
    override suspend fun addToBasket(menu: BasketMenuDomain) {
        try {
            val response = dataSource.addToBasket(menu.toDomain())
            Result.Success(data = response)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.i("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun fetchFromBasket(): Result<List<MenuDomain>> {
        return try {
            val response = dataSource.fetchFromBasket().map { it.toDomain() }
            Result.Success(data = response)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.i("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun fetchBasketById(objectId: String): Result<MenuDomain> {
        return try {
            val params = "{\"objectId\":\"$objectId\"}"
            val response = dataSource.fetchBasketById(params)
            val reserved = response.first().toDomain()
            Result.Success(data = reserved)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }
}
package com.example.socialapp.data.cloud.source

import android.util.Log
import com.example.socialapp.data.cloud.models.basket.BasketMenu
import com.example.socialapp.data.cloud.models.menu.MenuCloud
import com.example.socialapp.data.cloud.service.BasketService
import java.util.concurrent.CancellationException
import javax.inject.Inject

class FetchFromBasketCloudDataSourceImpl @Inject constructor(
    private val service: BasketService
) : FetchFromBasketCloudDataSource {

    override suspend fun fetchFromBasket(): List<MenuCloud> {
        return try {
            val response = service.fetchFromBasket()
            if (response.isSuccessful) response.body()?.results ?: emptyList()
            else emptyList()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.i("RestaurantApp", e.stackTraceToString())
            emptyList()
        }
    }

    override suspend fun addToBasket(menu: BasketMenu) {
        try {
            val response = service.addToBasket(menu)
            if (response.isSuccessful) response.body() ?: String()
            else String()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.i("RestaurantApp", e.stackTraceToString())
            String()
        }
    }

    override suspend fun fetchBasketById(objectId: String): List<MenuCloud> {
        return try {
            val reserved = service.fetchBasketById(objectId)
            if (reserved.isSuccessful) reserved.body()?.results ?: emptyList()
            else emptyList()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            emptyList()
        }
    }
}
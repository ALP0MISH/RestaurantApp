package com.example.socialapp.data.cloud.service

import com.example.socialapp.data.cloud.models.basket.BasketBody
import com.example.socialapp.data.cloud.models.basket.BasketMenu
import com.example.socialapp.data.cloud.models.menu.MenuResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val RESERVE_ORDER = "ReserveOrder"
private const val WHERE = "where"


interface BasketService {

    @GET(RESERVE_ORDER)
    suspend fun fetchFromBasket(): Response<MenuResponse>

    @POST(RESERVE_ORDER)
    suspend fun addToBasket(
        @Body params: BasketMenu,
    ): Response<BasketBody>

    @GET(RESERVE_ORDER)
    suspend fun fetchBasketById(
        @Query(WHERE) query: String
    ): Response<MenuResponse>
}
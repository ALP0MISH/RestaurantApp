package com.example.socialapp.data.cloud.service

import com.example.socialapp.data.cloud.models.menu.CategoryCloud
import com.example.socialapp.data.cloud.models.menu.CategoryResponse
import com.example.socialapp.data.cloud.models.menu.MenuResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val DRINKS = "Drinks"
private const val DESSERTS = "Desserts"
private const val FAST_FOOD = "FastFood"
private const val SALADS = "Salads"
private const val HOT_DISHES = "HotDishes"
private const val WHERE = "where"
private const val CATEGORY = "Category"


interface MenuService {

    @GET(CATEGORY)
    suspend fun getCategories(
        @Query(WHERE) objectId: String
    ): Response<CategoryResponse>

    @GET(DRINKS)
    suspend fun fetchDrinkById(
        @Query(WHERE) query: String
    ): Response<MenuResponse>

    @GET(DESSERTS)
    suspend fun fetchDessertsById(
        @Query(WHERE) query: String
    ): Response<MenuResponse>

    @GET(FAST_FOOD)
    suspend fun fetchFastFoodById(
        @Query(WHERE) query: String
    ): Response<MenuResponse>

    @GET(HOT_DISHES)
    suspend fun fetchHotDishesById(
        @Query(WHERE) query: String
    ): Response<MenuResponse>

    @GET(SALADS)
    suspend fun fetchSaladsById(
        @Query(WHERE) query: String
    ): Response<MenuResponse>

    @GET(DRINKS)
    suspend fun fetchAllDrinks(): Response<MenuResponse>

    @GET(DESSERTS)
    suspend fun fetchAllDesserts(): Response<MenuResponse>

    @GET(FAST_FOOD)
    suspend fun fetchAllFastFood(): Response<MenuResponse>

    @GET(HOT_DISHES)
    suspend fun fetchAllHotDishes(): Response<MenuResponse>

    @GET(SALADS)
    suspend fun fetchAllSalads(): Response<MenuResponse>

}
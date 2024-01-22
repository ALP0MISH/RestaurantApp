package com.example.restaurantapp.presentation.screens.detail_screen

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface DetailDestination {

    val route: String
}

object DetailsScrenDestination : DetailDestination {

    const val menuId = "menuId"
    const val categoryId = "categoryId"
    override val route: String = "detail_screen"
    val routeWithArgs = "$route/{$menuId}/{$categoryId}"
    val arguments = listOf(navArgument(menuId) { type = NavType.StringType })
}
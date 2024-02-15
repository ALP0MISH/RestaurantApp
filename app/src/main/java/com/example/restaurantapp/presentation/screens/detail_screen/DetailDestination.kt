package com.example.restaurantapp.presentation.screens.detail_screen

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface DetailDestination {

    val route: String
}

object DetailsScreenDestination : DetailDestination {
    const val menuId = "menuId"
    const val categoryId = "categoryId"
    override val route: String = "detail_screen"
    val routeWithArgs: String = "$route/{$menuId}/{$categoryId}"
    fun getRouteWithArgs(categoryId: String?): String {
        return if (categoryId != null) {
            "$route/{$menuId}/{$categoryId}"
        } else {
            "$route/{$menuId}"
        }
    }

    val arguments = listOf(
        navArgument(menuId) { type = NavType.StringType }
    )
}

package com.example.restaurantapp.presentation.screens.select_category

import com.example.restaurantapp.presentation.navigations.Destination

object SelectCategoryDestination : Destination {
    override fun route(): String = "select_category_destination"
    override fun routeWithArgs(): String = route()
}
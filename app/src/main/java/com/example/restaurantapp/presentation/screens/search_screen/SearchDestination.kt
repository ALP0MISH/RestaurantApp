package com.example.restaurantapp.presentation.screens.search_screen
import com.example.restaurantapp.presentation.navigations.Destination

object SearchDestination : Destination {
    override fun route(): String  = "search_screen_route"
    override fun routeWithArgs(): String  = route()
}
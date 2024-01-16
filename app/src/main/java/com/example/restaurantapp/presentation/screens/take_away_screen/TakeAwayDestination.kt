package com.example.restaurantapp.presentation.screens.take_away_screen

import com.example.restaurantapp.presentation.navigations.Destination

object TakeAwayDestination : Destination {
    override fun route(): String = "take_away_destination"

    override fun routeWithArgs(): String = route()
}
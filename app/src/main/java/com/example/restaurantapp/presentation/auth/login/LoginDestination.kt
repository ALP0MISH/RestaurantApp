package com.example.restaurantapp.presentation.auth.login

import com.example.restaurantapp.presentation.navigations.Destination

object LoginDestination : Destination {
    override fun route(): String = "login_destination"
    override fun routeWithArgs(): String = route()
}
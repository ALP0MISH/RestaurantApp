package com.example.restaurantapp.presentation.screens.splash
import com.example.restaurantapp.presentation.navigations.Destination

object SplashDestination : Destination {
    override fun route(): String  = "splash_screen_route"
    override fun routeWithArgs(): String  = route()
}
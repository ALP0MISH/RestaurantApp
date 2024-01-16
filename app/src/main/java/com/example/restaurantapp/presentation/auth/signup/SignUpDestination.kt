package com.example.restaurantapp.presentation.auth.signup

import com.example.restaurantapp.presentation.navigations.Destination

object SignUpDestination : Destination {
    override fun route(): String = "signup_screen"
    override fun routeWithArgs(): String = route()
}
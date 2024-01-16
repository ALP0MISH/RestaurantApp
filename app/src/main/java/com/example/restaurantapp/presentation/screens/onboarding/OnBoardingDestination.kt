package com.example.restaurantapp.presentation.screens.onboarding

import com.example.restaurantapp.presentation.navigations.Destination

object OnBoardingDestination : Destination {
    override fun route(): String = "onboarding_destination"
    override fun routeWithArgs(): String = route()
}
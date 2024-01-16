package com.example.restaurantapp.presentation.navigations

interface Destination {
    fun route(): String
    fun routeWithArgs(): String
}

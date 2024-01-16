package com.example.restaurantapp.domain.repository

import com.example.restaurantapp.domain.models.UserDomain

interface CurrentUserRepository {
    fun saveCurrentUser(user:UserDomain)
    fun fetchCurrentUser(): UserDomain
    fun clearCurrentUser()
    fun isOnboardingPassed(): Boolean
    fun setOnboardingShowed()
    fun clearOnBoarding()
}
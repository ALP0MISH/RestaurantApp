package com.example.restaurantapp.domain.repository

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.CreateResponseDomain
import com.example.restaurantapp.domain.models.UserDomain

interface LoginRepository {
    suspend fun signIn(email: String, password: String): Result<UserDomain>
    suspend fun signUp(
        email: String,
        lastName: String,
        name: String,
        password: String,
    ): Result<CreateResponseDomain>
}
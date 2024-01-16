package com.example.restaurantapp.domain.use_cases.signIn

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.UserDomain

interface SignInUseCase {
    suspend operator fun invoke(email: String, password: String): Result<UserDomain>
}
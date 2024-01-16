package com.example.restaurantapp.domain.use_cases.signup

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.UserDomain

interface SignUpUseCase {
    suspend operator fun invoke(
        email: String,
        lastName: String,
        name: String,
        password: String
    ): Result<UserDomain>
}
package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.UserDomain

interface FetchUserFromCloudUseCase {
    suspend fun fetchUserFromCloud(): Result<UserDomain>

}
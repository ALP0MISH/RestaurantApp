package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.UserDomain
import com.example.restaurantapp.domain.repository.UserRepository

class FetchUserFromCloudUseCaseImpl(
    private val repository: UserRepository
) : FetchUserFromCloudUseCase {
    override suspend fun fetchUserFromCloud(): Result<UserDomain> {
        return repository.fetchAllUsers()
    }
}
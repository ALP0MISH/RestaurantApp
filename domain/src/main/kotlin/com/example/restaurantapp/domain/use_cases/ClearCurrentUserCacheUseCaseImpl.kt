package com.example.restaurantapp.domain.use_cases

import com.example.restaurantapp.domain.repository.CurrentUserRepository
import com.example.restaurantapp.domain.repository.UserRepository

class ClearCurrentUserCacheUseCaseImpl(
    private val userRepository: CurrentUserRepository
) : ClearCurrentUserCacheUseCase {
    override fun clearCurrentUserCache() = userRepository.clearCurrentUser()
}
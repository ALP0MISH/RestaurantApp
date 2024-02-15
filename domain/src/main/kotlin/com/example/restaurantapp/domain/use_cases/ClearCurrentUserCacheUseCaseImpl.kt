package com.example.restaurantapp.domain.use_cases

import com.example.restaurantapp.domain.repository.CurrentUserRepository

class ClearCurrentUserCacheUseCaseImpl(
    private val userRepository: CurrentUserRepository
) : ClearCurrentUserCacheUseCase {
    override fun clearCurrentUserCache() = userRepository.clearCurrentUser()
}
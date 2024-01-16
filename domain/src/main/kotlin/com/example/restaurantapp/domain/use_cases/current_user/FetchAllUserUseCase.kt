package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.models.UserDomain
import com.example.restaurantapp.domain.repository.CurrentUserRepository

interface FetchAllUserUseCase {
    suspend operator fun invoke(): UserDomain
}

class FetchUserUseCaseImpl(
    private val currentUserRepository: CurrentUserRepository
) : FetchAllUserUseCase {
    override suspend fun invoke(): UserDomain {
        val currentUser = currentUserRepository.fetchCurrentUser()
       return currentUser
    }
}
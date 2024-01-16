package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.models.UserDomain
import com.example.restaurantapp.domain.repository.CurrentUserRepository

interface FetchCurrentUserUseCase {
    operator fun invoke(): UserDomain
}

class FetchCurrentUserUseCaseImpl(
    private val repository: CurrentUserRepository
) : FetchCurrentUserUseCase {
    override fun invoke(): UserDomain {
        return repository.fetchCurrentUser()
    }

}
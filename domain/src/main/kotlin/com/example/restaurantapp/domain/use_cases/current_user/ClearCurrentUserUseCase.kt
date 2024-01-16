package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.repository.CurrentUserRepository

interface ClearCurrentUserUseCase {
    operator fun invoke()
}

class ClearCurrentUserUseCaseImpl(
    private val repository: CurrentUserRepository
): ClearCurrentUserUseCase{
    override fun invoke() {
        return repository.clearCurrentUser()
    }

}
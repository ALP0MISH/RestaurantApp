package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.models.UserDomain
import com.example.restaurantapp.domain.repository.CurrentUserRepository

interface SaveCurrentUserUseCase {
    operator fun invoke(user: UserDomain)
}

class SaveCurrentUserUseCaseImpl(
    private val repository: CurrentUserRepository
) : SaveCurrentUserUseCase {
    override fun invoke(user: UserDomain) {
        repository.saveCurrentUser(user)
    }

}
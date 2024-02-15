package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.models.ChangeUserInfoDomain
import com.example.restaurantapp.domain.repository.UserRepository

class ChangeUserInfoUseCaseImpl(
    private val repository: UserRepository
) : ChangeUserInfoUseCase {
    override suspend fun changeUserInfo(menu: ChangeUserInfoDomain) {
        repository.changeUserInfo(menu)
    }
}
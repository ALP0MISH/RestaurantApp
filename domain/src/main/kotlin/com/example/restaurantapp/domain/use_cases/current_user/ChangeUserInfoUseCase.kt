package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.models.ChangeUserInfoDomain

interface ChangeUserInfoUseCase {
    suspend fun changeUserInfo(menu: ChangeUserInfoDomain)

}
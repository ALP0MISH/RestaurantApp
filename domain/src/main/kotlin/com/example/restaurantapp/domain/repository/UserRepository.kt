package com.example.restaurantapp.domain.repository

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.ChangeUserInfoDomain
import com.example.restaurantapp.domain.models.UserDomain

interface UserRepository {

    suspend fun fetchUserById(id: String): Result<UserDomain>

    suspend fun deleteUserById(id: String)

    suspend fun changeUserInfo(menu: ChangeUserInfoDomain)

    suspend fun fetchAllUsers(): Result<UserDomain>
}
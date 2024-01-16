package com.example.socialapp.data.repositories

import android.util.Log
import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.domain.models.UserDomain
import com.example.restaurantapp.domain.repository.UserRepository
import com.example.socialapp.data.cloud.service.UserService
import com.example.socialapp.data.mappers.toDomain
import java.util.concurrent.CancellationException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: UserService,
) : UserRepository {
    override suspend fun fetchUserById(id: String): Result<UserDomain> {
        return try {
            val params = "{\"objectId\":\"$id\"}"
            val response = service.fetchUserById(params)
            val user = response.body()?.results?.first()?.toDomain() ?: UserDomain.unknown
            Result.Success(data = user)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RestaurantApp", e.stackTraceToString())
            Result.Error(message = e.message ?: e.stackTraceToString())
        }
    }

    override suspend fun deleteUserById(id: String) {
    }

    override suspend fun fetchAllUsers(): Result<List<UserDomain>> {
        return try {
            val userCloud = service.fetchAllUsers().body()?.results ?: emptyList()
            Result.Success(data = userCloud.map { it.toDomain() })
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("SocialApp", e.stackTraceToString())
            Result.Error(message = e.message ?: e.stackTraceToString())
        }
    }
}
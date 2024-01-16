package com.example.socialapp.data.cloud.service

import com.example.socialapp.data.cloud.models.user.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val USERS = "Users"
private const val WHERE = "where"

interface UserService {

    @GET(USERS)
    suspend fun fetchUserById(
        @Query(WHERE) params: String,
    ): Response<UserResponse>

    @GET(USERS)
    suspend fun fetchAllUsers(): Response<UserResponse>
}
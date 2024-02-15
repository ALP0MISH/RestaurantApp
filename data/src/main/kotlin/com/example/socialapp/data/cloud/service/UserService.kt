package com.example.socialapp.data.cloud.service

import com.example.socialapp.data.cloud.models.user.ChangeUserInfoResponse
import com.example.socialapp.data.cloud.models.user.CreateResponse
import com.example.socialapp.data.cloud.models.user.UserCloud
import com.example.socialapp.data.cloud.models.user.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

private const val USERS = "Users"
private const val USERS_ID = "Users/{objectId}"
private const val WHERE = "where"
private const val OBJECT_ID = "objectId"


interface UserService {

    @GET(USERS)
    suspend fun fetchUserById(
        @Query(WHERE) params: String,
    ): Response<UserResponse>

    @GET(USERS)
    suspend fun fetchAllUsers(): Response<UserResponse>

    @PUT(USERS_ID)
    suspend fun changeUserInfo(
        @Path(OBJECT_ID) objectId: String,
        @Body params: ChangeUserInfoResponse,
    ): Response<UserCloud>
}
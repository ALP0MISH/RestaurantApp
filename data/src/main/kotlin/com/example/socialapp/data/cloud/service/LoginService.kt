package com.example.socialapp.data.cloud.service

import com.example.socialapp.data.cloud.models.user.CreateResponse
import com.example.socialapp.data.cloud.models.user.SignUpParams
import com.example.socialapp.data.cloud.models.user.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val USERS = "Users"
private const val WHERE = "where"

interface   LoginService {

    @GET(USERS)
    suspend fun signIn(
        @Query(WHERE) params: String
    ): Response<UserResponse>

    @POST(USERS)
    suspend fun signUp(
        @Body params: SignUpParams
    ): Response<CreateResponse?>
}
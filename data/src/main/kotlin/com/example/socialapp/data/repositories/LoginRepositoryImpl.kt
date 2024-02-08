package com.example.socialapp.data.repositories

import android.util.Log
import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.CreateResponseDomain
import com.example.restaurantapp.domain.models.UserDomain
import com.example.restaurantapp.domain.repository.LoginRepository
import com.example.restaurantapp.domain.use_cases.current_user.SaveCurrentUserObjectId
import com.example.restaurantapp.domain.use_cases.current_user.SaveCurrentUserUseCase
import com.example.socialapp.data.cloud.models.user.SignUpParams
import com.example.socialapp.data.cloud.service.LoginService
import com.example.socialapp.data.mappers.toDomain
import java.util.concurrent.CancellationException
import javax.inject.Inject

const val DEFAULT_ERROR_MESSAGE = "Something went Wrong"

class LoginRepositoryImpl @Inject constructor(
    private val service: LoginService,
    private val saveCurrentUserUseCase: SaveCurrentUserObjectId
) : LoginRepository {

    override suspend fun signIn(
        email: String,
        password: String
    ): Result<UserDomain> {
        return try {
            val response = service.signIn("{\"email\":\"$email\", \"password\":\"$password\"}")
            val result = response.body()?.results!!.first()
            Result.Success(data = result.toDomain())
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("Abdurahman", e.stackTraceToString())
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    override suspend fun signUp(
        email: String,
        lastName: String,
        name: String,
        password: String
    ): Result<CreateResponseDomain> = try {
        val params = SignUpParams(
            email = email,
            lastName = lastName,
            name = name,
            password = password
        )
        val response = service.signUp(params)
        val result = response.body()
        if (result != null) {
            saveCurrentUserUseCase(result.id)
            Result.Success(CreateResponseDomain(id = result.id))
        }
        else Result.Error(DEFAULT_ERROR_MESSAGE)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Log.e("abdurahman", e.stackTraceToString())
        Result.Error(DEFAULT_ERROR_MESSAGE)
    }
}

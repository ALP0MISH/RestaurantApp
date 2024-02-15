package com.example.socialapp.data.cloud.source

import android.util.Log
import com.example.restaurantapp.domain.repository.CurrentUserRepository
import com.example.socialapp.data.cloud.models.user.ChangeUserInfoResponse
import com.example.socialapp.data.cloud.service.UserService
import com.example.socialapp.data.mappers.toDomain
import java.util.concurrent.CancellationException
import javax.inject.Inject

class ChangeUserInfoDataSoursImpl @Inject constructor(
    private val service: UserService,
    private val saveCurrentUserUseCase: CurrentUserRepository,
    private val userService: UserService,
) : ChangeUserInfoDataSours {
    override suspend fun changeUserInfo(menu: ChangeUserInfoResponse) {
        try {
            val params = saveCurrentUserUseCase.fetchCurrentUser()
            val response = service.changeUserInfo(
                params = menu,
                objectId = params.objectId,
            )
            if (response.isSuccessful) {
                val usId = "{\"objectId\":\"${params.objectId}\"}"
                val responseGetUser = userService.fetchUserById(usId)
                if (responseGetUser.isSuccessful) {
                    val fetchedUser = responseGetUser.body()
                    saveCurrentUserUseCase.clearCurrentUser()
                    fetchedUser?.results?.firstOrNull()?.toDomain()
                        ?.let { saveCurrentUserUseCase.saveCurrentUser(it) }
                }
            } else String()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.i("RestaurantApp", e.stackTraceToString())
            String()
        }
    }
}
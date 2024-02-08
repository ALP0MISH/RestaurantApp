package com.example.socialapp.data.cloud.source

import android.util.Log
import com.example.restaurantapp.domain.repository.CurrentUserRepository
import com.example.restaurantapp.domain.use_cases.current_user.FetchCurrentUserUseCase
import com.example.restaurantapp.domain.use_cases.current_user.SaveCurrentUserUseCase
import com.example.socialapp.data.cloud.models.user.ChangeUserInfoResponse
import com.example.socialapp.data.cloud.service.UserService
import java.util.concurrent.CancellationException
import javax.inject.Inject

class ChangeUserInfoDataSoursImpl @Inject constructor(
    private val service: UserService,
    private val saveCurrentUserUseCase: CurrentUserRepository,
) : ChangeUserInfoDataSours {
    override suspend fun changeUserInfo(menu: ChangeUserInfoResponse) {
        try {
            val params = "{\"objectId\":\"${saveCurrentUserUseCase.getSavedUserId()}\"}"
            val response = service.changeUserInfo(
                params = menu,
                objectId = params,
            )
            if (response.isSuccessful) response.body()?.id ?: String()
            else String()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.i("RestaurantApp", e.stackTraceToString())
            String()
        }
    }
}
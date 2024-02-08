package com.example.socialapp.data.cloud.source

import com.example.socialapp.data.cloud.models.user.ChangeUserInfoResponse

interface ChangeUserInfoDataSours {
    suspend fun changeUserInfo(menu: ChangeUserInfoResponse)

}
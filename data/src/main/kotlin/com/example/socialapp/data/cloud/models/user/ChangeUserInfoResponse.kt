package com.example.socialapp.data.cloud.models.user

import com.google.gson.annotations.SerializedName

private const val EMAIL = "email"
private const val HISTORY_ID = "history_id"
private const val PASSWORD = "password"
private const val OBJECT_ID = "objectId"
private const val LASTNAME = "last_name"
private const val NAME = "name"

data class ChangeUserInfoResponse(
    @SerializedName(OBJECT_ID)
    val objectId: String,
    @SerializedName(EMAIL)
    val email: String,
    @SerializedName(HISTORY_ID)
    val historyId: String?,
    @SerializedName(PASSWORD)
    val password: String,
    @SerializedName(LASTNAME)
    val userLastname: String,
    @SerializedName(NAME)
    val userName: String
) : java.io.Serializable {
    companion object {
        val unknown = ChangeUserInfoResponse(
            email = String(),
            historyId = String(),
            password = String(),
            userLastname = String(),
            userName = String(),
            objectId = String()
        )
    }
}
package com.example.restaurantapp.domain.models

data class ChangeUserInfoDomain(
    val email: String,
    val historyId: String?,
    val password: String,
    val userLastname: String,
    val objectId: String,
    val userName: String
) {
    companion object {
        val unknown = ChangeUserInfoDomain(
            email = String(),
            historyId = String(),
            password = String(),
            userName = String(),
            userLastname = String(),
            objectId = String()
        )
    }
}
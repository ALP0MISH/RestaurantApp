package com.example.restaurantapp.domain.models

data class ChangeUserInfoDomain(
    val email: String,
    val password: String,
    val userLastname: String,
    val userName: String,
    val aboutMe: String?,

    ) {
    companion object {
        val unknown = ChangeUserInfoDomain(
            email = String(),
            password = String(),
            userName = String(),
            userLastname = String(),
            aboutMe = String()
        )
    }
}
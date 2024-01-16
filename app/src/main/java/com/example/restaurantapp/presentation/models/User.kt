package com.example.restaurantapp.presentation.models

import com.example.restaurantapp.domain.models.UserDomain

data class User(
    val createdAt: String,
    val email: String,
    val historyId: String?,
    val isReserveFoodId: String?,
    val isReserveTableId: String?,
    val location: Location?,
    val objectId: String,
    val password: String,
    val updatedAt: String,
    val userAvatar: String?,
    val userLastname: String,
    val userName: String
) {

    fun isUnknown() = this == unknown

    fun isNotUnknown() = this != unknown

    companion object {
        val unknown = User(
            isReserveTableId = "",
            createdAt = "",
            email = "",
            userLastname = "UnKnown",
            location = null,
            userName = "UnKnown",
            objectId = String(),
            password = String(),
            userAvatar = null,
            updatedAt = String(),
            isReserveFoodId = String(),
            historyId = String(),
        )
    }
}

fun UserDomain.toUser() = this.run {
    if (this == UserDomain.unknown) return@run User.unknown
    User(
        createdAt = createdAt,
        email = email,
        isReserveTableId = isReserveTableId,
        isReserveFoodId = isReserveFoodId,
        userAvatar = userAvatar,
        location = if (location != null) Location(
            latitude = location!!.latitude,
            longitude = location!!.longitude,
            type = location!!.type,
        ) else null,
        userName = userName,
        userLastname = userLastname,
        historyId = historyId,
        updatedAt = updatedAt,
        password = password,
        objectId = objectId,
    )
}
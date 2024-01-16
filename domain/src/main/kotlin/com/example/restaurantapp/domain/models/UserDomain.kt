package com.example.restaurantapp.domain.models


data class UserDomain(
    val createdAt: String,
    val email: String,
    val historyId: String,
    val isReserveFoodId: String?,
    val isReserveTableId: String?,
    val location: LocationDomain?,
    val objectId: String,
    val password: String,
    val updatedAt: String,
    val userAvatar: String?,
    val userLastname: String,
    val userName: String
) {
    companion object {
        val unknown = UserDomain(
            isReserveTableId = "",
            createdAt = "",
            email = "",
            userLastname = "UnKnown",
            location = null,
            userName = "UnKnown",
            objectId = String(),
            password = String(),
            userAvatar = String(),
            updatedAt = String(),
            isReserveFoodId = String(),
            historyId = String(),
        )
    }
}
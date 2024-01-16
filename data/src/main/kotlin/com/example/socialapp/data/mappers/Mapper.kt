package com.example.socialapp.data.mappers

import com.example.restaurantapp.domain.models.LocationDomain
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.domain.models.UserDomain
import com.example.socialapp.data.cloud.models.user.UserCloud
import com.example.socialapp.data.cloud.models.menu.MenuCloud

fun UserCloud.toDomain(): UserDomain = this.run {
    UserDomain(
        userAvatar = userAvatar?.url,
        isReserveTableId = isReserveTableId ?: "",
        isReserveFoodId = isReserveFoodId ?: "",
        createdAt = createdAt,
        userLastname = userLastname,
        userName = userName,
        location = if (location != null) LocationDomain(
            latitude = location.latitude,
            longitude = location.longitude,
            type = location.type
        ) else null,
        objectId = objectId,
        password = password,
        updatedAt = updatedAt,
        historyId = historyId ?: "",
        email = email
    )
}

fun MenuCloud.toDomain(): MenuDomain =
    this.run {
        MenuDomain(
            objectId = objectId,
            createdAt = createdAt,
            gram = gram,
            price = price,
            title = title,
            updatedAt = updatedAt,
            image = image.url,
            destination = description ,
            category_id = category_id,
            rating = rating
        )
    }
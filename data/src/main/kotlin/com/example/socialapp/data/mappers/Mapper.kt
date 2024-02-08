package com.example.socialapp.data.mappers

import com.example.restaurantapp.domain.models.ChangeUserInfoDomain
import com.example.restaurantapp.domain.models.LocationDomain
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.domain.models.UserDomain
import com.example.socialapp.data.cloud.models.menu.MenuCloud
import com.example.socialapp.data.cloud.models.user.ChangeUserInfoResponse
import com.example.socialapp.data.cloud.models.user.UserCloud

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
            imageUrl = image.url,
            destination = description,
            categoryId = category_id,
            rating = rating,
            imageType = image.type,
            imageName = image.name,
        )
    }

fun ChangeUserInfoResponse.toDomain(): ChangeUserInfoDomain =
    this.run {
        ChangeUserInfoDomain(
            userLastname = userLastname,
            userName = userName,
            email = email,
            password = password,
            historyId = historyId,
            objectId = objectId
        )
    }

fun ChangeUserInfoDomain.toCache(): ChangeUserInfoResponse =
    this.run {
        ChangeUserInfoResponse(
            userLastname = userLastname,
            userName = userName,
            email = email,
            password = password,
            historyId = historyId,
            objectId = objectId
        )
    }
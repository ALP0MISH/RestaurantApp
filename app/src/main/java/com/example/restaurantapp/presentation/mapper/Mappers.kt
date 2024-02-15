package com.example.restaurantapp.presentation.mapper

import com.example.restaurantapp.domain.models.BasketMenuDomain
import com.example.restaurantapp.domain.models.ChangeUserInfoDomain
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.presentation.models.BasketMenuUi
import com.example.restaurantapp.presentation.models.MenuUi
import com.example.restaurantapp.presentation.screens.edit_profile.EditProfileUiState

fun MenuDomain.toUi(): MenuUi =
    MenuUi(
        objectId = objectId,
        title = title,
        gram = gram,
        price = price,
        description = destination,
        categoryId = categoryId,
        createdMenuAt = createdAt,
        rating = rating,
        updatedMenuAt = updatedAt,
        imageUrl = imageUrl,
        imageName = imageName,
        imageType = imageType
    )

fun MenuUi.toUi(): MenuDomain =
    MenuDomain(
        objectId = objectId,
        title = title,
        gram = gram,
        price = price,
        destination = description,
        categoryId = categoryId,
        createdAt = createdMenuAt,
        rating = rating,
        updatedAt = updatedMenuAt,
        imageUrl = imageUrl,
        imageName = imageName,
        imageType = imageType
    )

fun BasketMenuDomain.toUi(): BasketMenuUi =
    BasketMenuUi(
        title = title,
        gram = gram,
        price = price,
        description = description,
        rating = rating,
    )

fun MenuUi.toMenuUi(): BasketMenuDomain =
    BasketMenuDomain(
        title = title,
        gram = gram,
        price = price,
        description = description,
        categoryId = categoryId,
        rating = rating,
        imageUrl = imageUrl,
        imageName = imageName,
        imageType = imageType
    )

fun EditProfileUiState.toUi(): ChangeUserInfoDomain =
    ChangeUserInfoDomain(
        userName = name,
        userLastname = lastName,
        email = email,
        password = password,
        aboutMe = aboutMe
    )

fun ChangeUserInfoDomain.toUi(): EditProfileUiState =
    EditProfileUiState(
        name = userName,
        lastName = userLastname,
        email = email,
        image = "",
        password = password
    )


package com.example.restaurantapp.presentation.mapper

import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.presentation.models.MenuUi

fun MenuDomain.toUi(): MenuUi =
    MenuUi(
        objectId = objectId,
        title = title,
        gram = gram,
        price = price,
        description = destination,
        category_id = category_id,
        createdAt = createdAt,
        rating = rating,
        updatedAt = updatedAt,
        image = image
    )
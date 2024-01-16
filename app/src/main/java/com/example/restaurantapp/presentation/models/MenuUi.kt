package com.example.restaurantapp.presentation.models

import javax.annotation.concurrent.Immutable

@Immutable
data class MenuUi(
    val createdAt: String,
    val gram: String,
    val image: String,
    val objectId: String,
    val price: String,
    val title: String,
    val category_id: String,
    val description: String,
    val updatedAt: String,
    val rating: Float
) {
    companion object {
        val unknown = MenuUi(
            createdAt = String(),
            gram = String(),
            image = String(),
            objectId = String(),
            price = String(),
            title = String(),
            updatedAt = String(),
            category_id = String(),
            description = String(),
            rating = 0.0f
        )
    }
}
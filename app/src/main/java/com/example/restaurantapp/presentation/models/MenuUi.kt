package com.example.restaurantapp.presentation.models

import javax.annotation.concurrent.Immutable

@Immutable
data class MenuUi(
    val createdMenuAt: String,
    val gram: String,
    val imageUrl: String,
    val imageName: String,
    val imageType: String,
    val objectId: String,
    val price: String,
    val title: String,
    val categoryId: String,
    val description: String,
    val updatedMenuAt: String,
    val rating: String,
) {
    companion object {
        val unknown = MenuUi(
            createdMenuAt = String(),
            gram = String(),
            imageUrl = String(),
            objectId = String(),
            price = String(),
            title = String(),
            updatedMenuAt = String(),
            categoryId = String(),
            description = String(),
            rating = String(),
            imageName = String(),
            imageType = String()
        )
    }
}
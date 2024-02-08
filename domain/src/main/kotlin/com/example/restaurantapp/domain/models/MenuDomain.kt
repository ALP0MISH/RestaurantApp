package com.example.restaurantapp.domain.models

import java.awt.Image


data class MenuDomain(
    val createdAt: String,
    val gram: String,
    val imageUrl: String,
    val imageName: String,
    val imageType: String,
    val objectId: String,
    val price: String,
    val title: String,
    val categoryId: String,
    val destination: String,
    val updatedAt: String,
    val rating: String
) {
    companion object {
        val unknown = MenuDomain(
            createdAt = String(),
            gram = String(),
            imageUrl = String(),
            objectId = String(),
            price = String(),
            title = String(),
            updatedAt = String(),
            categoryId = String(),
            destination = String(),
            rating = String(),
            imageType = String(),
            imageName = String()
        )
    }
}

data class MenuBasket(
    val createdAt: String,
    val gram: String,
    val image: Image,
    val objectId: String,
    val price: String,
    val title: String,
    val category_id: String,
    val destination: String,
    val updatedAt: String,
    val rating: Float
)
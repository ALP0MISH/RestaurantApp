package com.example.restaurantapp.domain.models


data class MenuDomain(
    val createdAt: String,
    val gram: String,
    val image: String,
    val objectId: String,
    val price: String,
    val title: String,
    val category_id: String,
    val destination: String,
    val updatedAt: String,
    val rating: Float
) {
    companion object {
        val unknown = MenuDomain(
            createdAt = String(),
            gram = String(),
            image = String(),
            objectId = String(),
            price = String(),
            title = String(),
            updatedAt = String(),
            category_id = String(),
            destination = String(),
            rating = 0.0f
        )
    }
}
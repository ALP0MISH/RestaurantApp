package com.example.restaurantapp.domain.models


data class CategoryDomain(
    val createdAt: String,
    val name: String,
    val objectId: String,
    val updatedAt: String
) {
    companion object {
        val unknown = CategoryDomain(
            createdAt = String(),
            name = String(),
            objectId = String(),
            updatedAt = String()
        )
    }
}
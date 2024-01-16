package com.example.restaurantapp.presentation.models

import javax.annotation.concurrent.Immutable

@Immutable
data class CategoryUI(
    val createdAt: String,
    val name: String,
    val objectId: String,
    val updatedAt: String
) {
    companion object {
        val unknown = CategoryUI(
            createdAt = String(),
            name = String(),
            objectId = String(),
            updatedAt = String()
        )
    }
}
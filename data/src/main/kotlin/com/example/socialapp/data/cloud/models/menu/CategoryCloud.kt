package com.example.socialapp.data.cloud.models.menu

import com.example.restaurantapp.domain.models.CategoryDomain


data class CategoryCloud(
    val objectId: String,
    val name: String,
    val createdAt: String,
    val updatedAt: String,
) {
    companion object {
        val unknown = CategoryCloud(
            objectId = String(),
            name = String(),
            createdAt = String(),
            updatedAt = String(),
        )
    }
}

fun CategoryCloud.toDomain() = CategoryDomain(
    objectId = this.objectId,
    name = this.name,
    updatedAt = this.updatedAt,
    createdAt = this.createdAt,
)


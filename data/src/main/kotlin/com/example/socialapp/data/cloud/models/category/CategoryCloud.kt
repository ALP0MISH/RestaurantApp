package com.example.socialapp.data.cloud.models.category


import com.example.restaurantapp.domain.models.CategoryDomain
import com.google.gson.annotations.SerializedName

private const val CREATED_AT = "createdAt"
private const val NAME = "name"
private const val OBJECT_ID = "objectId"
private const val UPDATE_AT = "updatedAt"

data class CategoryCloud(
    @SerializedName(CREATED_AT)
    val createdAt: String,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(OBJECT_ID)
    val objectId: String,
    @SerializedName(UPDATE_AT)
    val updatedAt: String
) : java.io.Serializable {

    companion object {
        val unknown = CategoryCloud(
            createdAt = String(),
            name = String(),
            objectId = String(),
            updatedAt = String()
        )
    }
}

fun CategoryCloud.toDomain(): CategoryDomain =
    this.run {
        CategoryDomain(
            createdAt = createdAt,
            name = name,
            objectId = objectId,
            updatedAt = updatedAt
        )
    }
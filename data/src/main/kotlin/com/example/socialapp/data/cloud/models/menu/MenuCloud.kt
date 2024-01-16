package com.example.socialapp.data.cloud.models.menu


import com.example.restaurantapp.domain.models.MenuDomain
import com.google.gson.annotations.SerializedName

private const val CREATE_AT = "createdAt"
private const val GRAM = "gram"
private const val IMAGE = "image"
private const val OBJECT_ID = "objectId"
private const val PRICE = "price"
private const val TITLE = "title"
private const val CATEGORY_ID = "category_id"
private const val UPDATED_AT = "updatedAt"
private const val DESCRIPTION = "description"
private const val RATING = "rating"

data class MenuCloud(
    @SerializedName(CREATE_AT)
    val createdAt: String,
    @SerializedName(GRAM)
    val gram: String,
    @SerializedName(IMAGE)
    val image: Image,
    @SerializedName(OBJECT_ID)
    val objectId: String,
    @SerializedName(PRICE)
    val price: String,
    @SerializedName(TITLE)
    val title: String,
    @SerializedName(CATEGORY_ID)
    val category_id: String,
    @SerializedName(UPDATED_AT)
    val updatedAt: String,
    @SerializedName(DESCRIPTION)
    val description: String,
    @SerializedName(RATING)
    val rating: Float
) : java.io.Serializable

fun MenuCloud.toDomain() = this.run {
    MenuDomain(
        createdAt = createdAt,
        gram = gram,
        image = image.url,
        objectId = objectId,
        price = price,
        title = title,
        updatedAt = updatedAt,
        category_id = category_id,
        destination = description,
        rating = rating
    )
}
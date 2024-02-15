package com.example.socialapp.data.cloud.models.basket


import com.example.restaurantapp.domain.models.BasketMenuDomain
import com.example.socialapp.data.cloud.models.menu.Image
import com.google.gson.annotations.SerializedName

private const val CREATE_AT = "createdAt"
private const val GRAM = "gram"
private const val IMAGE = "image"
private const val OBJECT_ID = "object_menu"
private const val PRICE = "price"
private const val TITLE = "title"
private const val CATEGORY_ID = "category_id"
private const val UPDATED_AT = "updatedAt"
private const val DESCRIPTION = "description"
private const val RATING = "rating"
private const val NAME = "name"
private const val TYPE = "__type"

data class BasketMenu(
    @SerializedName(GRAM)
    val gram: String,
    @SerializedName(IMAGE)
    val image: Image,
    @SerializedName(PRICE)
    val price: String,
    @SerializedName(TITLE)
    val title: String,
    @SerializedName(CATEGORY_ID)
    val category_id: String,
    @SerializedName(DESCRIPTION)
    val description: String,
    @SerializedName(RATING)
    val rating: String
) : java.io.Serializable

data class BasketImage(
    @SerializedName(NAME)
    val name: String,
    @SerializedName(TYPE)
    val type: String,
) : java.io.Serializable

fun BasketMenu.toDomain() = this.run {
    BasketMenuDomain(
        gram = gram,
        imageUrl = image.url,
        price = price,
        title = title,
        categoryId = category_id,
        description = description,
        rating = rating,
        imageName = image.name,
        imageType = image.type
    )
}

fun BasketMenuDomain.toDomain() = this.run {
    BasketMenu(
        gram = gram,
        image = Image(
            url = imageUrl,
            name = imageName,
            type = imageType,
        ),
        price = price,
        title = title,
        category_id = categoryId,
        description = description,
        rating = rating,
    )
}
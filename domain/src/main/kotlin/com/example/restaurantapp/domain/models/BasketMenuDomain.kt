package com.example.restaurantapp.domain.models


data class BasketMenuDomain(
    val gram: String,
    val imageUrl: String,
    val imageName: String,
    val imageType: String,
    val price: String,
    val title: String,
    val categoryId: String,
    val description: String,
    val rating: String
) : java.io.Serializable {

    companion object {
        val unknown = BasketMenuDomain(
            gram = String(),
            imageUrl = String(),
            price = String(),
            title = String(),
            categoryId = String(),
            description = String(),
            rating = String(),
            imageName = String(),
            imageType = String()
        )
    }
}

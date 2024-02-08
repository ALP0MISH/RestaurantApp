package com.example.restaurantapp.presentation.models

import javax.annotation.concurrent.Immutable

@Immutable
data class BasketMenuUi(
    val gram: String,
    val price: String,
    val title: String,
    val category_id: String,
    val description: String,
    val rating: String,
) : java.io.Serializable {

    companion object {
        val unknown = BasketMenuUi(
            gram = String(),
            price = String(),
            title = String(),
            category_id = String(),
            description = String(),
            rating = String(),
        )
    }
}

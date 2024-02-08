package com.example.socialapp.data.cloud.models.basket

import com.google.gson.annotations.SerializedName

private const val OBJECT_ID = "objectId"
private const val CREATED_AT = "createdAt"

data class BasketBody(
    @SerializedName(OBJECT_ID)
    val objectId: String,
    @SerializedName(CREATED_AT)
    val createdAt: String,
) {
    companion object {
        val unknown = BasketBody(
            objectId = "",
            createdAt = ""
        )
    }
}
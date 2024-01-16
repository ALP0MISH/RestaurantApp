package com.example.socialapp.data.cloud.models.user

import com.google.gson.annotations.SerializedName

private const val OBJECT_ID = "objectId"

data class CreateResponse(
    @SerializedName(OBJECT_ID)
    val id: String,
)

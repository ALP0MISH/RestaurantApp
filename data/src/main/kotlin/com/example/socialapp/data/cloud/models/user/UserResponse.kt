package com.example.socialapp.data.cloud.models.user

import com.google.gson.annotations.SerializedName

private const val RESULTS = "results"

data class UserResponse(
    @SerializedName(RESULTS)
    val results: List<UserCloud>
) : java.io.Serializable
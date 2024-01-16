package com.example.socialapp.data.cloud.models.user

import com.google.gson.annotations.SerializedName

private const val NAME = "name"
private const val LASTNAME = "last_name"
private const val EMAIL = "email"
private const val PASSWORD = "password"

data class SignUpParams(
    @SerializedName(NAME)
    val name: String,
    @SerializedName(LASTNAME)
    val lastName: String,
    @SerializedName(EMAIL)
    val email: String,
    @SerializedName(PASSWORD)
    val password: String
)
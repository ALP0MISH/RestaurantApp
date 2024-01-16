package com.example.socialapp.data.cloud.models.user


import com.google.gson.annotations.SerializedName

private const val LATITUDE = "latitude"
private const val LONGITUDE = "longitude"
private const val TYPE = "__type"

data class Location(
    @SerializedName(LATITUDE)
    val latitude: Int,
    @SerializedName(LONGITUDE)
    val longitude: Int,
    @SerializedName(TYPE)
    val type: String
) : java.io.Serializable
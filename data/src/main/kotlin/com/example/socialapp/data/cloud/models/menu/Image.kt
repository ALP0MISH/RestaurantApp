package com.example.socialapp.data.cloud.models.menu


import com.google.gson.annotations.SerializedName

private const val NAME = "name"
private const val TYPE = "__type"
private const val URL = "url"

data class Image(
    @SerializedName(NAME)
    val name: String,
    @SerializedName(TYPE)
    val type: String,
    @SerializedName(URL)
    val url: String
) : java.io.Serializable
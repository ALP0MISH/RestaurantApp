package com.example.socialapp.data.cloud.models.menu


import com.google.gson.annotations.SerializedName
import java.io.Serializable

private const val NAME = "name"
private const val TYPE = "__type"
private const val URL = "url"

data class Image(
    @SerializedName(NAME)
    val name: String = "icon.png",
    @SerializedName(TYPE)
    val type: String = "File",
    @SerializedName(URL)
    val url: String
) : Serializable
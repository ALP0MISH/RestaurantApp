package com.example.socialapp.data.cloud.models.menu


import com.google.gson.annotations.SerializedName

private const val RESULTS = "results"

data class MenuResponse(
    @SerializedName(RESULTS)
    val results: List<MenuCloud>
) : java.io.Serializable

data class CategoryResponse(
    @SerializedName(RESULTS)
    val categories: List<CategoryCloud>
)
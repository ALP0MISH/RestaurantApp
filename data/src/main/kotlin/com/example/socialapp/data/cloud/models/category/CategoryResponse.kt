package com.example.socialapp.data.cloud.models.category


import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("results")
    val results: List<CategoryCloud>
) :  java.io.Serializable
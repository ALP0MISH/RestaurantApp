package com.example.socialapp.data.cloud.models.user

import com.google.gson.annotations.SerializedName

private const val CREATED_AT = "createdAt"
private const val EMAIL = "email"
private const val HISTORY_ID = "history_id"
private const val IS_RESERVED_FOOD_ID = "is_reserve_food_id"
private const val IS_RESERVED_TABLE_ID = "is_reserve_table_id"
private const val LOCATION = "location"
private const val OBJECT_ID = "objectId"
private const val PASSWORD = "password"
private const val UPDATED_AT = "updatedAt"
private const val USER_AVATAR = "user_avatar"
private const val LASTNAME = "last_name"
private const val NAME = "name"

data class  UserCloud(
    @SerializedName(CREATED_AT)
    val createdAt: String,
    @SerializedName(EMAIL)
    val email: String,
    @SerializedName(HISTORY_ID)
    val historyId: String?,
    @SerializedName(IS_RESERVED_FOOD_ID)
    val isReserveFoodId: String?,
    @SerializedName(IS_RESERVED_TABLE_ID)
    val isReserveTableId: String?,
    @SerializedName(LOCATION)
    val location: Location?,
    @SerializedName(OBJECT_ID)
    val objectId: String,
    @SerializedName(PASSWORD)
    val password: String,
    @SerializedName(UPDATED_AT)
    val updatedAt: String,
    @SerializedName(USER_AVATAR)
    val userAvatar: UserAvatar?,
    @SerializedName(LASTNAME)
    val userLastname: String,
    @SerializedName(NAME)
    val userName: String
) : java.io.Serializable
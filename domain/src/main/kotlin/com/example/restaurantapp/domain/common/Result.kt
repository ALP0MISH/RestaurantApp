package com.example.restaurantapp.domain.common

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {
    val isSuccess: Boolean get() = this is Success

    val isFailure: Boolean get() = this is Error

    class Error<T>(message: String) : Result<T>(data = null, message = message)

    class Success<T>(data: T) : Result<T>(data = data, message = null)
}
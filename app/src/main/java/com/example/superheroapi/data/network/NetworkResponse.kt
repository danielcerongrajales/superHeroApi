package com.example.superheroapi.data.network


sealed class NetworkResponse<out T> {
    data class Success<out T>(val value: T): NetworkResponse<T>()
    data class GenericError(
        val code: Int? = null,
        val errorMessage: String? = null
    ): NetworkResponse<Nothing>()

    object NetworkError: NetworkResponse<Nothing>()
}


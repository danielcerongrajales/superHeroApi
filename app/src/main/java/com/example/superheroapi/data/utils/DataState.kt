package com.example.superheroapi.data.utils


sealed class DataState<T> {

    data class Error<T>(val response: StateMessage, val data: T? = null) : DataState<T>()

    data class Data<T>(val response: StateMessage?, val data: T? = null) : DataState<T>()

}


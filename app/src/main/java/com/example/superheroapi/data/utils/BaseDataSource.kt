package com.example.superheroapi.data.utils


import com.example.superheroapi.data.network.NetworkConstants.NETWORK_TIMEOUT
import com.example.superheroapi.data.network.NetworkErrors.ERROR_UNKNOWN
import com.example.superheroapi.data.network.NetworkErrors.NETWORK_ERROR_TIMEOUT
import com.example.superheroapi.data.network.NetworkErrors.NETWORK_ERROR_UNKNOWN
import com.example.superheroapi.data.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException


suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T?
): NetworkResponse<T?> {
    return withContext(dispatcher) {
        try {
            withTimeout(NETWORK_TIMEOUT) {
                NetworkResponse.Success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    val code = 408 // timeout error code
                    NetworkResponse.GenericError(code, NETWORK_ERROR_TIMEOUT)
                }
                is IOException -> {
                    NetworkResponse.NetworkError
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)

                    NetworkResponse.GenericError(
                        code,
                        errorResponse
                    )
                }
                else -> {
                    NetworkResponse.GenericError(
                        null,
                        NETWORK_ERROR_UNKNOWN
                    )
                }
            }
        }
    }
}


private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        ERROR_UNKNOWN
    }
}
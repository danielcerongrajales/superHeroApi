package com.example.superheroapi.data.network

import com.example.superheroapi.data.network.NetworkErrors.NETWORK_DATA_NULL
import com.example.superheroapi.data.network.NetworkErrors.NETWORK_ERROR
import com.example.superheroapi.data.utils.DataState
import com.example.superheroapi.data.utils.MessageType
import com.example.superheroapi.data.utils.Response
import com.example.superheroapi.data.utils.StateMessage


abstract class ApiResponseHandler<ViewState, Data>(
    private val response: NetworkResponse<Data?>,
) {

    suspend fun getResult(): DataState<ViewState>? {

        return when (response) {

            is NetworkResponse.GenericError -> {
                DataState.Error(
                    response = StateMessage(
                        Response(
                            message = "Reason: ${response.errorMessage.toString()}",
                            messageType = MessageType.Error
                        )
                    ),
                )
            }

            is NetworkResponse.NetworkError -> {
                DataState.Error(
                    response = StateMessage(
                        Response(
                            message = "Reason: $NETWORK_ERROR",
                            messageType = MessageType.Error
                        )
                    ),
                )
            }

            is NetworkResponse.Success -> {
                if (response.value == null) {
                    DataState.Error(
                        response = StateMessage(
                            Response(
                                message = "Reason: ${NETWORK_DATA_NULL}.",
                                messageType = MessageType.Error
                            )
                        ),
                    )
                } else {

                    handleSuccess(resultObj = response.value)
                }
            }
        }
    }
    abstract suspend fun handleSuccess(resultObj: Data): DataState<ViewState>?

}
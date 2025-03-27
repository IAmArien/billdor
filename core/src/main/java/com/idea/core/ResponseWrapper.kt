package com.idea.core

sealed class ResponseWrapper<out T> {

    data class ResponseSuccess<out T>(val value: T) : ResponseWrapper<T>()
 
    data class ResponseFailure(
        val statusCode: Int = 0,
        val errorMessage: String = "",
        val throwable: Throwable? = null
    ) : ResponseWrapper<Nothing>()

    data class RemoteNetworkError(
        val errorMessage: String = "",
        val throwable: Throwable? = null
    ) : ResponseWrapper<Nothing>()

    data class LocalNetworkError(
        val errorMessage: String = "",
        val throwable: Throwable? = null
    ) : ResponseWrapper<Nothing>()

    data class UnknownError(
        val errorMessage: String = "",
        val throwable: Throwable? = null
    ) : ResponseWrapper<Nothing>()
}

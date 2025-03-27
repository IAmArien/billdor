package com.idea.billdor.repository

import com.idea.billdor.frameworks.api.handlers.ApiErrorHandler
import com.idea.core.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException

open class BaseRepository : ApiErrorHandler() {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResponseWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResponseWrapper.ResponseSuccess(apiCall.invoke())
            } catch (error: Throwable) {
                Timber.e("safeApiCall-e: $error")
                val apiError = checkError(error).getApiErrorWrapper()
                when (error) {
                    is SocketTimeoutException -> {
                        ResponseWrapper.RemoteNetworkError(
                            errorMessage = apiError.errorMessage,
                            throwable = apiError.errorThrowable
                        )
                    }
                    is ConnectException -> {
                        ResponseWrapper.RemoteNetworkError(
                            errorMessage = apiError.errorMessage,
                            throwable = apiError.errorThrowable
                        )
                    }
                    is IOException -> {
                        ResponseWrapper.LocalNetworkError(
                            errorMessage = apiError.errorMessage,
                            throwable = apiError.errorThrowable
                        )
                    }
                    is HttpException -> {
                        ResponseWrapper.ResponseFailure(
                            statusCode = apiError.statusCode,
                            errorMessage = apiError.errorMessage,
                            throwable = apiError.errorThrowable
                        )
                    }
                    else -> {
                        ResponseWrapper.UnknownError(
                            errorMessage = apiError.errorMessage,
                            throwable = apiError.errorThrowable
                        )
                    }
                }
            }
        }
    }
}

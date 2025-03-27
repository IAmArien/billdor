package com.idea.billdor.frameworks.api.handlers

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException

enum class HttpErrors(val error: String) {
    HTTP_400_ERROR(error = "Error 400: Bad Request"),
    HTTP_401_ERROR(error = "Error 401: Unauthorized"),
    HTTP_403_ERROR(error = "Error 403: Access Forbidden"),
    HTTP_404_ERROR(error = "Error 404: Resource Not Found"),
    HTTP_422_ERROR(error = "Error 422: Unprocessable Entity"),
    HTTP_500_ERROR(error = "Error 500: Internal Server Error"),
    HTTP_502_ERROR(error = "Error 502: Bad Gateway"),
    HTTP_503_ERROR(error = "Error 503: Service Unavailable"),
    HTTP_GEN_ERROR(error = "Unknown error encountered")
}

enum class NetworkErrors(val message: String) {
    SOCKET_TIMEOUT_ERROR(message = "Request Time Out"),
    CONNECTION_ERROR(message = "An error occurred while attempting to connect to the server"),
    IO_EXCEPTION_ERROR(message = "No Internet Connection"),
    UNKNOWN_ERROR(message = "Unknown error encountered");
}

data class ApiErrorWrapper(
    val statusCode: Int = -1,
    val errorMessage: String = "",
    val errorBody: String = "",
    val errorThrowable: Throwable
)

open class ApiErrorHandler {
    
    private lateinit var apiErrorWrapper: ApiErrorWrapper

    /**
     * Function Used To Check Error When Failed To Get The Result From The API
     * @param error A Throwable Exception Thrown While Performing Network Request
     * @return Its Base Class
     */
    fun checkError(error: Throwable): ApiErrorHandler {
        when (error) {
            is SocketTimeoutException -> {
                apiErrorWrapper = ApiErrorWrapper(
                    errorMessage = NetworkErrors.SOCKET_TIMEOUT_ERROR.message, errorThrowable = error
                )
            }
            is ConnectException -> {
                apiErrorWrapper = ApiErrorWrapper(
                    errorMessage = NetworkErrors.CONNECTION_ERROR.message, errorThrowable = error
                )
            }
            is IOException -> {
                apiErrorWrapper = ApiErrorWrapper(
                    errorMessage = NetworkErrors.IO_EXCEPTION_ERROR.message, errorThrowable = error
                )
            }
            is HttpException -> {
                when (error.code()) {
                    400 -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_400_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    401 -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_401_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    403 -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_403_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    404 -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_404_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    422 -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_422_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    500 -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_500_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    502 -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_502_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    503 -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_503_ERROR.error,
                            errorThrowable = error
                        )
                    }
                    else -> {
                        apiErrorWrapper = ApiErrorWrapper(
                            statusCode = error.code(),
                            errorMessage = HttpErrors.HTTP_GEN_ERROR.error,
                            errorThrowable = error
                        )
                    }
                }
            }
            else -> {
                apiErrorWrapper = ApiErrorWrapper(
                    errorMessage = NetworkErrors.UNKNOWN_ERROR.message, errorThrowable = error
                )
            }
        }
        return this
    }

    fun getApiErrorWrapper(): ApiErrorWrapper = this.apiErrorWrapper
}

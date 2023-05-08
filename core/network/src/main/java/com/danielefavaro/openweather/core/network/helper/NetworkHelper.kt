package com.danielefavaro.openweather.core.network.helper

import com.danielefavaro.openweather.core.model.exception.OWConnectionError
import com.danielefavaro.openweather.core.model.exception.OWGenericError
import com.danielefavaro.openweather.core.model.exception.OWNotFoundError
import com.danielefavaro.openweather.core.model.exception.OWTimeoutError
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        try {
            apiCall.invoke()
        } catch (throwable: Throwable) {
            when (throwable) {
                is TimeoutException, is SocketTimeoutException -> {
                    throw OWTimeoutError()
                }

                is IOException -> {
                    throw OWConnectionError()
                }

                is HttpException -> {
                    // TODO check for error codes
                    throw OWNotFoundError()
                }

                else -> {
                    throw OWGenericError()
                }
            }
        }
    }
}

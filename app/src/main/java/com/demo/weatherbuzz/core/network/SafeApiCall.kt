package com.demo.weatherbuzz.core.network

import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = apiCall()
        when {
            response.isSuccessful -> {
                response.body()?.let { NetworkResult.Success(it) }
                    ?: NetworkResult.Error(message = "Empty response body")
            }
            response.code() == 404 -> NetworkResult.Error(404, "City not found")
            response.code() == 401 -> NetworkResult.Error(401, "Invalid API key")
            response.code() == 429 -> NetworkResult.Error(429, "Rate limit exceeded. Try again later")
            else -> NetworkResult.Error(response.code(), response.message().ifEmpty { "Unknown error" })
        }
    } catch (e: UnknownHostException) {
        NetworkResult.Error(message = "No internet connection")
    } catch (e: SocketTimeoutException) {
        NetworkResult.Error(message = "Connection timed out")
    } catch (e: IOException) {
        NetworkResult.Error(message = "Network error occurred")
    } catch (e: Exception) {
        NetworkResult.Error(message = e.localizedMessage ?: "Unknown error occurred")
    }
}

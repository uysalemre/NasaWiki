package com.eu.citizenmecase.utils.network

import com.eu.citizenmecase.R
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): NetworkResult<T> {
    return try {
        NetworkResult.OnSuccess(apiCall())
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> NetworkResult.OnUnexpected(
                R.string.err_http_internet
            )
            is HttpException -> NetworkResult.OnUnexpected(
                R.string.err_http_unknown
            )
            else -> NetworkResult.OnUnexpected(R.string.err_http_unknown)
        }
    }
}
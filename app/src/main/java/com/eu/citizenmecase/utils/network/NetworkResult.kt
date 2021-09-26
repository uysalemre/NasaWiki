package com.eu.citizenmecase.utils.network

sealed class NetworkResult<out T> {
    data class OnSuccess<out T>(val data: T) : NetworkResult<T>()
    data class OnUnexpected(val messageId: Int) : NetworkResult<Nothing>()
    object OnFailure : NetworkResult<Nothing>()
    object OnLoading : NetworkResult<Nothing>()
}
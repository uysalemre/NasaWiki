package com.eu.nasawiki.utils.network

/**
 * @author Emre UYSAL
 * A sealed class for managing network result states and data
 */
sealed class NetworkResult<out T> {
    data class OnSuccess<out T>(val data: T) : NetworkResult<T>()
    data class OnUnexpected(val messageId: Int) : NetworkResult<Nothing>()
    object OnLoading : NetworkResult<Nothing>()
}
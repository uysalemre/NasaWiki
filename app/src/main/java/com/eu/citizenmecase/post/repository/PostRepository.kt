package com.eu.citizenmecase.post.repository

import com.eu.citizenmecase.post.repository.remote.Services
import com.eu.citizenmecase.utils.network.NetworkResult
import com.eu.citizenmecase.utils.network.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepository @Inject constructor(private val services: Services) {
    fun getPosts() = flow {
        emit(NetworkResult.OnLoading)
        emit(safeApiCall { services.getPosts() })
    }.flowOn(Dispatchers.IO)

    fun getComments() = flow {
        emit(NetworkResult.OnLoading)
        emit(safeApiCall { services.getComments() })
    }.flowOn(Dispatchers.IO)

    fun getPhotos() = flow {
        emit(NetworkResult.OnLoading)
        emit(safeApiCall { services.getPhotos() })
    }.flowOn(Dispatchers.IO)
}
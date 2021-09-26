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

    fun getComments(postId: Long) = flow {
        emit(NetworkResult.OnLoading)
        emit(safeApiCall { services.getComments(postId) })
    }.flowOn(Dispatchers.IO)

    fun getPhoto(id: Long) = flow {
        emit(NetworkResult.OnLoading)
        emit(safeApiCall { services.getPhoto(id) })
    }.flowOn(Dispatchers.IO)
}
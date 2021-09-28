package com.eu.citizenmecase.post.repository

import com.eu.citizenmecase.post.repository.remote.Services
import com.eu.citizenmecase.utils.db.AppDb
import com.eu.citizenmecase.utils.ext.convertToList
import com.eu.citizenmecase.utils.network.NetworkResult
import com.eu.citizenmecase.utils.network.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @author Emre UYSAL
 * Repository that send network requests and caches them
 * If a cache exists emits it otherwise creates cache if request is success
 */
class PostRepository @Inject constructor(private val services: Services, private val db: AppDb) {

    suspend fun getPhoto(id: Long) = getRequestOrCacheResult(
        { db.getDao().getAllPhotos(id).convertToList() },
        { data -> db.getDao().insertPhotos(data.convertToList()) },
        { safeApiCall { services.getPhoto(id) } }
    )

    suspend fun getPosts() = getRequestOrCacheResult(
        { db.getDao().getAllPosts().convertToList() },
        { data -> db.getDao().insertPosts(data.convertToList()) },
        { safeApiCall { services.getPosts() } }
    )

    suspend fun getComments(id: Long) = getRequestOrCacheResult(
        { db.getDao().getAllComments(id).convertToList() },
        { data -> db.getDao().insertComments(data.convertToList()) },
        { safeApiCall { services.getComments(id) } }
    )

    private fun <T> getRequestOrCacheResult(
        dbCacheQuery: suspend () -> List<T>,
        dbInsertQuery: suspend (List<T>) -> Unit,
        netWorkCall: suspend () -> NetworkResult<List<T>>
    ) = flow {
        val cache = dbCacheQuery()
        emit(
            when {
                cache.isNotEmpty() -> NetworkResult.OnSuccess(cache)
                else -> NetworkResult.OnLoading
            }
        )
        val call = netWorkCall()
        if (call is NetworkResult.OnSuccess<*>) {
            dbInsertQuery(call.data as List<T>)
        }
        emit(call)
    }.flowOn(Dispatchers.IO)
}
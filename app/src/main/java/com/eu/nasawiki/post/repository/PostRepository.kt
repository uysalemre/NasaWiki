package com.eu.nasawiki.post.repository

import com.eu.nasawiki.post.repository.remote.Services
import com.eu.nasawiki.utils.db.AppDb
import com.eu.nasawiki.utils.ext.convertEntityModel
import com.eu.nasawiki.utils.ext.convertToEntityModelList
import com.eu.nasawiki.utils.network.NetworkResult
import com.eu.nasawiki.utils.network.safeApiCall
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
        { db.getDao().getPhoto(id)?.convertEntityModel() },
        { data -> db.getDao().insertPhoto(data.convertEntityModel()) },
        { safeApiCall { services.getPhoto(id) } }
    )

    suspend fun getPosts() = getRequestOrCacheResult(
        { db.getDao().getAllPosts().convertToEntityModelList() },
        { data -> db.getDao().insertPosts(data.convertToEntityModelList()) },
        { safeApiCall { services.getPosts() } }
    )

    suspend fun getComments(id: Long) = getRequestOrCacheResult(
        { db.getDao().getAllComments(id).convertToEntityModelList() },
        { data -> db.getDao().insertComments(data.convertToEntityModelList()) },
        { safeApiCall { services.getComments(id) } }
    )

    private fun <T> getRequestOrCacheResult(
        dbCacheQuery: suspend () -> T?,
        dbInsertQuery: suspend (T) -> Unit,
        netWorkCall: suspend () -> NetworkResult<T>
    ) = flow {
        emit(
            when (val cache = dbCacheQuery()) {
                null -> NetworkResult.OnLoading
                else -> {
                    when (cache) {
                        is List<*> -> {
                            if (cache.isEmpty()) NetworkResult.OnLoading
                            else NetworkResult.OnSuccess(cache)
                        }
                        else -> NetworkResult.OnSuccess(cache)
                    }
                }
            }
        )
        val call = netWorkCall()
        if (call is NetworkResult.OnSuccess<*>) {
            dbInsertQuery(call.data as T)
        }
        emit(call)
    }.flowOn(Dispatchers.IO)
}
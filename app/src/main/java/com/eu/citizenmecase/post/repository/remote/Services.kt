package com.eu.citizenmecase.post.repository.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Emre UYSAL
 * Network services used for requests
 */
interface Services {
    @GET("posts")
    suspend fun getPosts(): List<PostModel>

    @GET("comments")
    suspend fun getComments(@Query("postId") postId: Long): List<CommentModel>

    @GET("photos")
    suspend fun getPhoto(@Query("albumId") albumId: Long): List<PhotoModel>
}
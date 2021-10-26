package com.eu.nasawiki.post.repository.remote

import retrofit2.http.*

/**
 * @author Emre UYSAL
 * Network services used for requests
 */
interface Services {
    @GET("posts")
    suspend fun getPosts(): List<PostModel>

    @GET("comments")
    suspend fun getComments(@Query("postId") postId: Long): List<CommentModel>

    @GET("photos/{id}")
    suspend fun getPhoto(@Path("id") id: Long): PhotoModel

    @PATCH("photos/{id}")
    suspend fun updateIsFav(@Path("id") id: Long, @Body model: PhotoModel): PhotoModel
}
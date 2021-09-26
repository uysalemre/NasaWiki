package com.eu.citizenmecase.post.repository.remote

import retrofit2.http.GET

interface Services {
    @GET("posts")
    suspend fun getPosts(): List<PostModel>

    @GET("comments")
    suspend fun getComments(): List<CommentModel>

    @GET("photos")
    suspend fun getPhotos(): List<PhotoModel>
}
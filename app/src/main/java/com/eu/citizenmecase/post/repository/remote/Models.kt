package com.eu.citizenmecase.post.repository.remote

data class PostModel(
    val userId: Long,
    val title: String,
    val body: String,
    val thumbnailUrl: String,
    override val id: Long
) : Model

data class CommentModel(
    val postId: Long,
    val name: String,
    val email: String,
    val body: String,
    override val id: Long
) : Model

data class PhotoModel(
    val albumId: Long,
    val title: String,
    val url: String,
    override val id: Long
) : Model

sealed interface Model {
    val id: Long
}
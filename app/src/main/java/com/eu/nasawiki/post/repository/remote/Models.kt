package com.eu.nasawiki.post.repository.remote

/**
 * @author Emre UYSAL
 * Models that represents network data
 */

sealed interface Model {
    val id: Long
}

data class PostModel(
    val title: String,
    val summary: String,
    val url: String,
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
    val body: String,
    val url: String,
    val isFav: Boolean,
    override val id: Long
) : Model
package com.eu.citizenmecase.utils.ext

import com.eu.citizenmecase.post.repository.local.CommentEntity
import com.eu.citizenmecase.post.repository.local.PhotoEntity
import com.eu.citizenmecase.post.repository.local.PostEntity
import com.eu.citizenmecase.post.repository.remote.CommentModel
import com.eu.citizenmecase.post.repository.remote.PhotoModel
import com.eu.citizenmecase.post.repository.remote.PostModel

/**
 * @author Emre UYSAL
 * Extension file for adding functionalities to existing structures
 */
inline fun <reified T, R> List<T>.convertToList(): List<R> = map {
    when (it) {
        is PostModel -> {
            PostEntity(it.userId, it.title, it.body, it.thumbnailUrl, it.id)
        }
        is CommentModel -> {
            CommentEntity(it.postId, it.name, it.email, it.body, it.id)
        }
        is PhotoModel -> {
            PhotoEntity(it.albumId, it.title, it.url, it.id)
        }
        is PostEntity -> {
            PostModel(it.userId, it.title, it.body, it.thumbnailUrl, it.id)
        }
        is CommentEntity -> {
            CommentModel(it.postId, it.name, it.email, it.body, it.id)
        }
        is PhotoEntity -> {
            PhotoModel(it.albumId, it.title, it.url, it.id)
        }
        else -> throw IllegalArgumentException("No Model Like That")
    }
} as List<R>
package com.eu.nasawiki.utils.ext

import com.eu.nasawiki.post.repository.local.CommentEntity
import com.eu.nasawiki.post.repository.local.PhotoEntity
import com.eu.nasawiki.post.repository.local.PostEntity
import com.eu.nasawiki.post.repository.remote.CommentModel
import com.eu.nasawiki.post.repository.remote.PhotoModel
import com.eu.nasawiki.post.repository.remote.PostModel

/**
 * @author Emre UYSAL
 * Extension file for adding functionalities to existing structures
 */
inline fun <reified T, R> List<T>.convertToEntityModelList(): List<R> = map {
    when (it) {
        is PostModel -> {
            PostEntity(it.title, it.summary, it.url, it.id)
        }
        is CommentModel -> {
            CommentEntity(it.postId, it.name, it.email, it.body, it.id)
        }
        is PostEntity -> {
            PostModel(it.title, it.summary, it.url, it.id)
        }
        is CommentEntity -> {
            CommentModel(it.postId, it.name, it.email, it.body, it.id)
        }
        else -> throw IllegalArgumentException("No Model Like That")
    }
} as List<R>

inline fun <reified T, R> T.convertEntityModel(): R = when (this) {
    is PhotoEntity -> {
        PhotoModel(this.albumId, this.title, this.body, this.url, this.isFav, this.id)
    }
    is PhotoModel -> {
        PhotoEntity(this.albumId, this.title, this.body, this.url, this.isFav, this.id)
    }
    else -> throw IllegalArgumentException("No Model Like That")
} as R
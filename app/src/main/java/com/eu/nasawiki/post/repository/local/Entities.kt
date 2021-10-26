package com.eu.nasawiki.post.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Emre UYSAL
 * Entities that caches network data and represents db tables
 */
@Entity
data class PostEntity(
    val title: String,
    val summary: String,
    val url: String,
    val isFav: Boolean,
    @PrimaryKey val id: Long
)

@Entity
data class CommentEntity(
    val postId: Long,
    val name: String,
    val email: String,
    val body: String,
    @PrimaryKey val id: Long
)

@Entity
data class PhotoEntity(
    val albumId: Long,
    val title: String,
    val body: String,
    val url: String,
    val isFav: Boolean,
    @PrimaryKey val id: Long
)
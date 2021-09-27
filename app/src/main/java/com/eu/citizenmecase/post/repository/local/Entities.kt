package com.eu.citizenmecase.post.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    val userId: Long,
    val title: String,
    val body: String,
    val thumbnailUrl: String,
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
    val url: String,
    @PrimaryKey val id: Long
)
package com.eu.citizenmecase.post.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM PostEntity")
    fun getAllPosts(): List<PostEntity>

    @Query("SELECT * FROM CommentEntity where postId = (:id)")
    fun getAllComments(id: Long): List<CommentEntity>

    @Query("SELECT * FROM PhotoEntity where albumId = (:id)")
    fun getAllPhotos(id: Long): List<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(comments: List<CommentEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(photos: List<PhotoEntity>)
}
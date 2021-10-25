package com.eu.nasawiki.post.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author Emre UYSAL
 * Data access object for entities contains queries for managing data
 */
@Dao
interface Dao {
    @Query("SELECT * FROM PostEntity")
    fun getAllPosts(): List<PostEntity>

    @Query("SELECT * FROM CommentEntity where postId = (:id)")
    fun getAllComments(id: Long): List<CommentEntity>

    @Query("SELECT * FROM PhotoEntity where id = (:id) limit 1")
    fun getPhoto(id: Long): PhotoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(comments: List<CommentEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photo: PhotoEntity)
}
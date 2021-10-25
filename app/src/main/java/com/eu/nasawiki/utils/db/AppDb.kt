package com.eu.nasawiki.utils.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eu.nasawiki.post.repository.local.CommentEntity
import com.eu.nasawiki.post.repository.local.Dao
import com.eu.nasawiki.post.repository.local.PhotoEntity
import com.eu.nasawiki.post.repository.local.PostEntity

/**
 * @author Emre UYSAL
 * An abstract class that provides room dao and creates db tables using entities
 */
@Database(
    entities = [PostEntity::class, CommentEntity::class, PhotoEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDb : RoomDatabase() {
    abstract fun getDao(): Dao
}
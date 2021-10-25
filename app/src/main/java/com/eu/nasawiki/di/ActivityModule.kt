package com.eu.nasawiki.di

import com.eu.nasawiki.post.repository.PostRepository
import com.eu.nasawiki.post.repository.remote.Services
import com.eu.nasawiki.utils.db.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

/**
 * @author Emre UYSAL
 * Hilt module that creates repository object for fragments and lives until activity destroy
 */
@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    @Singleton
    fun providesRepository(services: Services, db: AppDb) = PostRepository(services, db)
}
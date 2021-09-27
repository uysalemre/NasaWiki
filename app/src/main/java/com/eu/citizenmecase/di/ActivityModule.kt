package com.eu.citizenmecase.di

import com.eu.citizenmecase.post.repository.PostRepository
import com.eu.citizenmecase.post.repository.remote.Services
import com.eu.citizenmecase.utils.db.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun providesRepository(services: Services, db: AppDb) = PostRepository(services, db)
}
package com.example.bebettatask.di

import android.content.Context
import com.example.bebettatask.utils.JsonHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class HelpersModule {

    // File Storage
    @Singleton
    @Provides
    fun providesImageHelper(@ApplicationContext context: Context): JsonHelper {
        return JsonHelper(context)
    }
}
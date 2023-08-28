package com.notesfinalproject.di

import android.content.Context
import com.notesfinalproject.preferences.UserSP
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SPProviders {
    @Singleton
    @Provides
    fun providesUserSP(
        @ApplicationContext context: Context
    ) = UserSP(context)
}
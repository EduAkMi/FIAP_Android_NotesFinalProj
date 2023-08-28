package com.notesfinalproject.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseProviders {
    @Singleton
    @Provides
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun providesFirestore() = FirebaseFirestore.getInstance()
}
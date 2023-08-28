package com.notesfinalproject.di

import com.notesfinalproject.data.repository.INotesRepository
import com.notesfinalproject.data.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindNotesRepository(impl: NotesRepository): INotesRepository
}
package com.notesfinalproject.di

import com.notesfinalproject.data.datasource.INotesLocalDataSource
import com.notesfinalproject.data.datasource.INotesRemoteDataSource
import com.notesfinalproject.data.datasource.NotesLocalDataSource
import com.notesfinalproject.data.datasource.NotesRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    // region Notes
    @Binds
    fun bindNotesLocalDataSource(impl: NotesLocalDataSource): INotesLocalDataSource
    @Binds
    fun bindNotesRemoteDataSource(impl: NotesRemoteDataSource): INotesRemoteDataSource
    // endregion
}
package com.notesfinalproject.di

import android.content.Context
import androidx.room.Room
import com.notesfinalproject.data.database.AppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoProviders {
    private const val DB_NAME = "NotesDB"

    @Singleton
    @Provides
    fun providesRoom(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDB::class.java,
        DB_NAME
    ).build()

    @Provides
    @Singleton
    fun providesCardDao(appDb: AppDB) = appDb.getNotesDao()
}
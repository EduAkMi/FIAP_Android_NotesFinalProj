package com.notesfinalproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.notesfinalproject.data.dao.NotesDao
import com.notesfinalproject.data.entities.NotesEntity

@Database(
    entities = [
        NotesEntity::class
    ],
    version = 1,
    exportSchema = true
)

@TypeConverters(Converters::class)
abstract class AppDB : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao
}
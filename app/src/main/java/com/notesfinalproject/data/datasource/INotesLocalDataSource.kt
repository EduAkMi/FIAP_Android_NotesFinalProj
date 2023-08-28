package com.notesfinalproject.data.datasource

import com.notesfinalproject.enums.NoteColors
import com.notesfinalproject.models.NotesModel

interface INotesLocalDataSource {
    suspend fun addFromLocal(): String
    suspend fun addFromRemote(note: NotesModel)
    suspend fun updateTexts(id: String, title: String, description: String)
    suspend fun updateColor(id: String, color: NoteColors)
    suspend fun get(): List<NotesModel>
    suspend fun getSingle(id: String): NotesModel
    suspend fun delete(id: String)
    suspend fun clear()
}
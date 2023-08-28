package com.notesfinalproject.data.datasource

import com.notesfinalproject.models.NotesModel

interface INotesRemoteDataSource {
    suspend fun download(): List<NotesModel>
    suspend fun update(note: NotesModel)
    suspend fun delete(id: String)
}
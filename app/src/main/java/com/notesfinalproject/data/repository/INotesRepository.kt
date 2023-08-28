package com.notesfinalproject.data.repository

import com.notesfinalproject.enums.NoteColors
import com.notesfinalproject.models.NotesModel

interface INotesRepository {
    // region Local Functions
    suspend fun addLocalFromLocal(): String
    suspend fun addLocalFromRemote(note: NotesModel)
    suspend fun updateLocalTexts(id: String, title: String, description: String)
    suspend fun updateLocalColor(id: String, color: NoteColors)
    suspend fun getLocal(): List<NotesModel>
    suspend fun getLocalSingle(id: String): NotesModel
    suspend fun deleteLocal(id: String)
    suspend fun clearLocal()
    // endregion

    // region Remote Functions
    suspend fun download(): List<NotesModel>
    suspend fun updateRemote(note: NotesModel)
    suspend fun deleteRemote(id: String)
    // endregion
}
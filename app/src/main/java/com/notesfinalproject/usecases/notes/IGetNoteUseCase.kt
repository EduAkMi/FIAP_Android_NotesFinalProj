package com.notesfinalproject.usecases.notes

import com.notesfinalproject.models.NotesModel

interface IGetNoteUseCase {
    suspend fun get(id: String): NotesModel
}
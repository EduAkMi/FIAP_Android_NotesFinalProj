package com.notesfinalproject.usecases.notes

import com.notesfinalproject.models.NotesModel

interface IGetNotesUseCase {
    suspend fun get(): List<NotesModel>
}
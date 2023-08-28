package com.notesfinalproject.usecases.notes

interface IDeleteNoteUseCase {
    suspend fun delete(id: String)
}
package com.notesfinalproject.usecases.notes

interface ICreateNoteUseCase {
    suspend fun create(): String
}
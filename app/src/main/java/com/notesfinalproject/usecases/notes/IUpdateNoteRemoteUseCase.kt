package com.notesfinalproject.usecases.notes

interface IUpdateNoteRemoteUseCase {
    suspend fun update(id: String)
}
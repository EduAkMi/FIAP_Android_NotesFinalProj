package com.notesfinalproject.usecases.notes

interface IUpdateNoteTextsUseCase {
    suspend fun update(id: String, title: String, description: String)
}
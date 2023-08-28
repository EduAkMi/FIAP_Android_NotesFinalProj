package com.notesfinalproject.usecases.notes

import com.notesfinalproject.enums.NoteColors

interface IUpdateNoteColorUseCase {
    suspend fun update(id: String, color: NoteColors)
}
package com.notesfinalproject.usecases.notes

import com.notesfinalproject.data.repository.INotesRepository
import com.notesfinalproject.enums.NoteColors
import javax.inject.Inject

class UpdateNoteColorUseCase @Inject constructor(
    private val repository: INotesRepository
) : IUpdateNoteColorUseCase{
    override suspend fun update(id: String, color: NoteColors) {
        repository.updateLocalColor(id, color)
    }
}
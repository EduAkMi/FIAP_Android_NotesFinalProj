package com.notesfinalproject.usecases.notes

import com.notesfinalproject.data.repository.INotesRepository
import javax.inject.Inject

class UpdateNoteTextsUseCase @Inject constructor(
    private val repository: INotesRepository
) : IUpdateNoteTextsUseCase {
    override suspend fun update(id: String, title: String, description: String) {
        repository.updateLocalTexts(id, title, description)
    }
}
package com.notesfinalproject.usecases.notes

import com.notesfinalproject.data.repository.INotesRepository
import com.notesfinalproject.models.NotesModel
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val repository: INotesRepository
) : IGetNoteUseCase {
    override suspend fun get(id: String): NotesModel {
        return repository.getLocalSingle(id)
    }
}
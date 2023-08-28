package com.notesfinalproject.usecases.notes

import com.notesfinalproject.data.repository.INotesRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: INotesRepository
) : IDeleteNoteUseCase {
    override suspend fun delete(id: String) {
        repository.deleteLocal(id)
        repository.deleteRemote(id)
    }
}
package com.notesfinalproject.usecases.notes

import com.notesfinalproject.data.repository.INotesRepository
import javax.inject.Inject

class UpdateNoteRemoteUseCase @Inject constructor(
    private val repository: INotesRepository
) : IUpdateNoteRemoteUseCase {
    override suspend fun update(id: String) {
        val note = repository.getLocalSingle(id)
        repository.updateRemote(note)
    }
}
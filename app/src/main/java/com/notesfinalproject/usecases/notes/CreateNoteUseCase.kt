package com.notesfinalproject.usecases.notes

import com.notesfinalproject.data.repository.INotesRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    private val repository: INotesRepository
) : ICreateNoteUseCase {
    override suspend fun create(): String {
        return repository.addLocalFromLocal()
    }
}
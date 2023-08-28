package com.notesfinalproject.usecases.notes

import com.notesfinalproject.data.repository.INotesRepository
import com.notesfinalproject.models.NotesModel
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: INotesRepository
) : IGetNotesUseCase{
    override suspend fun get(): List<NotesModel> {
        return repository.getLocal()
    }
}
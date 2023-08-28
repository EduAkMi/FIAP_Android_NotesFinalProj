package com.notesfinalproject.usecases.notes

import com.notesfinalproject.data.repository.INotesRepository
import javax.inject.Inject

class DownloadNotesUseCase @Inject constructor(
    private val repository: INotesRepository
) : IDownloadNotesUseCase {
    override suspend fun download() {
        val notes = repository.download()
        notes.forEach { repository.addLocalFromRemote(it) }
    }
}
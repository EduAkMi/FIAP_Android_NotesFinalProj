package com.notesfinalproject.data.repository

import com.notesfinalproject.data.datasource.INotesLocalDataSource
import com.notesfinalproject.data.datasource.INotesRemoteDataSource
import com.notesfinalproject.enums.NoteColors
import com.notesfinalproject.models.NotesModel
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val localDataSource: INotesLocalDataSource,
    private val remoteDataSource: INotesRemoteDataSource
) : INotesRepository {
    // region Local Functions
    override suspend fun addLocalFromLocal(): String {
        return localDataSource.addFromLocal()
    }

    override suspend fun addLocalFromRemote(note: NotesModel) {
        localDataSource.addFromRemote(note)
    }

    override suspend fun updateLocalTexts(
        id: String,
        title: String,
        description: String
    ) {
        localDataSource.updateTexts(id, title, description)
    }

    override suspend fun updateLocalColor(id: String, color: NoteColors) {
        localDataSource.updateColor(id, color)
    }

    override suspend fun getLocal(): List<NotesModel> {
        return localDataSource.get()
    }

    override suspend fun getLocalSingle(id: String): NotesModel {
        return localDataSource.getSingle(id)
    }

    override suspend fun deleteLocal(id: String) {
        localDataSource.delete(id)
    }

    override suspend fun clearLocal() {
        localDataSource.clear()
    }
    // endregion

    // region Remote Functions
    override suspend fun download(): List<NotesModel> {
        return remoteDataSource.download()
    }

    override suspend fun updateRemote(note: NotesModel) {
        remoteDataSource.update(note)
    }

    override suspend fun deleteRemote(id: String) {
        remoteDataSource.delete(id)
    }
    // endregion
}
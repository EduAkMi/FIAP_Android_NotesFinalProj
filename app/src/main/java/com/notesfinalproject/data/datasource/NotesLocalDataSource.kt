package com.notesfinalproject.data.datasource

import com.google.firebase.auth.FirebaseAuth
import com.notesfinalproject.data.dao.NotesDao
import com.notesfinalproject.data.entities.NotesEntity
import com.notesfinalproject.data.entities.toModel
import com.notesfinalproject.enums.NoteColors
import com.notesfinalproject.models.NotesModel
import com.notesfinalproject.models.toEntity
import com.notesfinalproject.usecases.IGenerateRandomIdUseCase
import com.notesfinalproject.utils.FirestorePaths
import java.util.Date
import javax.inject.Inject

class NotesLocalDataSource @Inject constructor(
    private val auth: FirebaseAuth,
    private val dao: NotesDao,
    private val randomIdUseCase: IGenerateRandomIdUseCase
) : INotesLocalDataSource {
    override suspend fun addFromLocal(): String {
        val date = Date()
        val newId = randomIdUseCase.generate(FirestorePaths.NOTES)
        dao.add(
            NotesEntity(
                noteId = newId,
                userId = auth.uid.toString(),
                title = "",
                description = "",
                color = NoteColors.NONE,
                lastUpdated = date,
            )
        )
        return newId
    }

    override suspend fun addFromRemote(note: NotesModel) {
        dao.add(note.toEntity())
    }

    override suspend fun updateTexts(id: String, title: String, description: String) {
        val date = Date()
        dao.updateTexts(
            noteId = id,
            title = title,
            description = description,
            lastUpdated = date
        )
    }

    override suspend fun updateColor(id: String, color: NoteColors) {
        val date = Date()
        dao.updateColor(
            noteId = id,
            color = color,
            lastUpdated = date
        )
    }

    override suspend fun get(): List<NotesModel> {
        return dao.get().map { it.toModel() }
    }

    override suspend fun getSingle(id: String): NotesModel {
        return dao.getSingle(id).toModel()
    }

    override suspend fun delete(id: String) {
        dao.delete(id)
    }

    override suspend fun clear() {
        dao.clear()
    }
}
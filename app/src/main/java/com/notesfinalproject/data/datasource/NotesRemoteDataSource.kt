package com.notesfinalproject.data.datasource

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.notesfinalproject.models.NotesModel
import com.notesfinalproject.utils.FirestorePaths
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NotesRemoteDataSource @Inject constructor(
    firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : INotesRemoteDataSource {
    private val path = firestore
        .collection(FirestorePaths.NOTES)

    override suspend fun download(): List<NotesModel> {
        return try {
            val objects = path.whereEqualTo("userId", auth.uid.toString())
                .get().await().toObjects(NotesModel::class.java)
            objects
        } catch (e: Exception) {
            Log.i(TAG, e.message.toString())
            emptyList()
        }
    }

    override suspend fun update(note: NotesModel) {
        try {
            val document = path.document(note.id)
            document.set(note).await()
        } catch (e: Exception) {
            Log.i(TAG, e.message.toString())
        }
    }

    override suspend fun delete(id: String) {
        try {
            val document = path.document(id)
            document.delete().await()
        } catch (e: Exception) {
            Log.i(TAG, e.message.toString())
        }
    }

    companion object {
        private const val TAG = "NotesRemoteDataSource"
    }
}
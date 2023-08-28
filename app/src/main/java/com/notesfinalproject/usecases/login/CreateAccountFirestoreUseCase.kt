package com.notesfinalproject.usecases.login

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.notesfinalproject.models.UserModel
import com.notesfinalproject.utils.FirestorePaths
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CreateAccountFirestoreUseCase @Inject constructor(
    private val firestore: FirebaseFirestore
) : ICreateAccountFirestoreUseCase {
    override suspend fun create(id: String, name: String, email: String): Result<String> {
        val document = firestore.collection(FirestorePaths.USERS).document(id)
        val userModel = UserModel(
            id = id,
            name = name,
            email = email
        )
        return try {
            document.set(userModel).await()
            Result.success("")
        } catch (e: Exception) {
            Log.i(TAG, e.message.toString())
            Result.failure(Exception(e.message.toString()))
        }
    }

    companion object {
        private const val TAG = "CreateAccountFirestore"
    }
}
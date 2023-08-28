package com.notesfinalproject.usecases.user

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.notesfinalproject.models.UserModel
import com.notesfinalproject.utils.FirestorePaths
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DownloadUserInfoUseCase @Inject constructor(
    firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : IDownloadUserInfoUseCase {
    private val path = firestore
        .collection(FirestorePaths.USERS)

    override suspend fun download(): UserModel {
        Log.i(TAG, auth.uid.toString())
        return try {
            path.document(auth.uid.toString()).get().await().toObject(UserModel::class.java) ?: UserModel()
        } catch (e: Exception) {
            Log.i(TAG, e.message.toString())
            UserModel()
        }
    }

    companion object {
        private const val TAG = "DownloadUserInfoUseCase"
    }
}
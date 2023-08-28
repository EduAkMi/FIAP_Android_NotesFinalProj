package com.notesfinalproject.usecases.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val auth: FirebaseAuth
) : ILoginUseCase {
    override suspend fun login(email: String, password: String): Result<String> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(auth.uid.toString())
        } catch (e: Exception) {
            Log.i(TAG, e.toString())
            Result.failure(Exception(e.message.toString()))
        }
    }

    companion object {
        private const val TAG = "LoginUseCase"
    }
}
package com.notesfinalproject.usecases.login

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CreateAccountAuthUseCase @Inject constructor(
    private val auth: FirebaseAuth
) : ICreateAccountAuthUseCase {
    override suspend fun create(name: String, email: String, password: String): Result<String> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(auth.uid.toString())
        } catch (e: Exception) {
            Result.failure(Exception(e.message.toString()))
        }
    }
}
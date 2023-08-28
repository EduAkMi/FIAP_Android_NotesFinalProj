package com.notesfinalproject.usecases.login

interface ICreateAccountFirestoreUseCase {
    suspend fun create(id: String, name: String, email: String): Result<String>
}
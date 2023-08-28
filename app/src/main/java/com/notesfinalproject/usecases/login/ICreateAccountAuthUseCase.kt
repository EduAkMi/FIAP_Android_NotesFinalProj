package com.notesfinalproject.usecases.login

interface ICreateAccountAuthUseCase {
    suspend fun create(name: String, email: String, password: String): Result<String>
}
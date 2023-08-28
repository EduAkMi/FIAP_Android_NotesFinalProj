package com.notesfinalproject.usecases.login

interface ILoginUseCase {
    suspend fun login(email: String, password: String): Result<String>
}
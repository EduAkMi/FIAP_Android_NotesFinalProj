package com.notesfinalproject.usecases.user

interface IUpdateUserSPNameUseCase {
    suspend fun update(name: String)
}
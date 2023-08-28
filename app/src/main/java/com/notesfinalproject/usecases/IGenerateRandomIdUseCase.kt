package com.notesfinalproject.usecases

interface IGenerateRandomIdUseCase{
    suspend fun generate(path: String): String
}
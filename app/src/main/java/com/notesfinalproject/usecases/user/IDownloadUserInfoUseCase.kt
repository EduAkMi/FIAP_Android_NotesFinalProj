package com.notesfinalproject.usecases.user

import com.notesfinalproject.models.UserModel

interface IDownloadUserInfoUseCase {
    suspend fun download(): UserModel
}
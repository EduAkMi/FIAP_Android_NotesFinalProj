package com.notesfinalproject.usecases.user

import com.notesfinalproject.preferences.UserSP
import javax.inject.Inject

class UpdateUserSPNameUseCase @Inject constructor(
    private val userSP: UserSP
) : IUpdateUserSPNameUseCase{
    override suspend fun update(name: String) {
        userSP.setUserName(name)
    }
}
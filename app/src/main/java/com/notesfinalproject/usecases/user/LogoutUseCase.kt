package com.notesfinalproject.usecases.user

import com.google.firebase.auth.FirebaseAuth
import com.notesfinalproject.data.repository.INotesRepository
import com.notesfinalproject.preferences.UserSP
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userSP: UserSP,
    private val notesRepository: INotesRepository,
    private val auth: FirebaseAuth
) : ILogoutUseCase {
    override suspend fun logout() {
        userSP.clear()
        notesRepository.clearLocal()
        auth.signOut()
    }
}
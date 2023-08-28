package com.notesfinalproject.di

import com.notesfinalproject.usecases.GenerateRandomIdUseCase
import com.notesfinalproject.usecases.IGenerateRandomIdUseCase
import com.notesfinalproject.usecases.user.ILogoutUseCase
import com.notesfinalproject.usecases.user.LogoutUseCase
import com.notesfinalproject.usecases.login.CreateAccountAuthUseCase
import com.notesfinalproject.usecases.login.CreateAccountFirestoreUseCase
import com.notesfinalproject.usecases.login.ICreateAccountAuthUseCase
import com.notesfinalproject.usecases.login.ICreateAccountFirestoreUseCase
import com.notesfinalproject.usecases.login.ILoginUseCase
import com.notesfinalproject.usecases.login.LoginUseCase
import com.notesfinalproject.usecases.notes.CreateNoteUseCase
import com.notesfinalproject.usecases.notes.DeleteNoteUseCase
import com.notesfinalproject.usecases.notes.DownloadNotesUseCase
import com.notesfinalproject.usecases.notes.GetNoteUseCase
import com.notesfinalproject.usecases.notes.GetNotesUseCase
import com.notesfinalproject.usecases.notes.ICreateNoteUseCase
import com.notesfinalproject.usecases.notes.IDeleteNoteUseCase
import com.notesfinalproject.usecases.notes.IDownloadNotesUseCase
import com.notesfinalproject.usecases.notes.IGetNoteUseCase
import com.notesfinalproject.usecases.notes.IGetNotesUseCase
import com.notesfinalproject.usecases.notes.IUpdateNoteColorUseCase
import com.notesfinalproject.usecases.notes.IUpdateNoteRemoteUseCase
import com.notesfinalproject.usecases.notes.IUpdateNoteTextsUseCase
import com.notesfinalproject.usecases.notes.UpdateNoteColorUseCase
import com.notesfinalproject.usecases.notes.UpdateNoteRemoteUseCase
import com.notesfinalproject.usecases.notes.UpdateNoteTextsUseCase
import com.notesfinalproject.usecases.user.DownloadUserInfoUseCase
import com.notesfinalproject.usecases.user.IDownloadUserInfoUseCase
import com.notesfinalproject.usecases.user.IUpdateUserSPNameUseCase
import com.notesfinalproject.usecases.user.UpdateUserSPNameUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    // region Login
    @Binds
    fun bindsCreateAccountAuthUseCase(impl: CreateAccountAuthUseCase): ICreateAccountAuthUseCase
    @Binds
    fun bindsCreateAccountFirestoreUseCase(impl: CreateAccountFirestoreUseCase): ICreateAccountFirestoreUseCase
    @Binds
    fun bindsLoginUseCase(impl: LoginUseCase): ILoginUseCase
    // endregion

    // region Notes
    @Binds
    fun bindsDownloadNotesUseCase(impl: DownloadNotesUseCase): IDownloadNotesUseCase
    @Binds
    fun bindsCreateNoteUseCase(impl: CreateNoteUseCase): ICreateNoteUseCase
    @Binds
    fun bindsGetNoteUseCase(impl: GetNoteUseCase): IGetNoteUseCase
    @Binds
    fun bindsGetNotesUseCase(impl: GetNotesUseCase): IGetNotesUseCase
    @Binds
    fun bindsUpdateNoteTextUseCase(impl: UpdateNoteTextsUseCase): IUpdateNoteTextsUseCase
    @Binds
    fun bindsUpdateNoteColorUseCase(impl: UpdateNoteColorUseCase): IUpdateNoteColorUseCase
    @Binds
    fun bindsUpdateNoteRemoteUseCase(impl: UpdateNoteRemoteUseCase): IUpdateNoteRemoteUseCase
    @Binds
    fun bindsDeleteNoteUseCase(impl: DeleteNoteUseCase): IDeleteNoteUseCase
    // endregion

    @Binds
    fun bindsGenerateRandomIdUseCase(impl: GenerateRandomIdUseCase): IGenerateRandomIdUseCase

    // region User
    @Binds
    fun bindsDownloadUserInfoUseCase(impl: DownloadUserInfoUseCase): IDownloadUserInfoUseCase
    @Binds
    fun bindsUpdateUserSPNameUseCase(impl: UpdateUserSPNameUseCase): IUpdateUserSPNameUseCase
    @Binds
    fun bindsLogoutUseCase(impl: LogoutUseCase): ILogoutUseCase
    // endregion
}
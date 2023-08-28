package com.notesfinalproject.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.notesfinalproject.presentation.BaseViewModel
import com.notesfinalproject.usecases.login.ILoginUseCase
import com.notesfinalproject.usecases.notes.IDownloadNotesUseCase
import com.notesfinalproject.usecases.user.IDownloadUserInfoUseCase
import com.notesfinalproject.usecases.user.IUpdateUserSPNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val onLoginUseCase: ILoginUseCase,
    private val onDownloadNotesUseCase: IDownloadNotesUseCase,
    private val onDownloadUserInfoUseCase: IDownloadUserInfoUseCase,
    private val onUpdateUserSPNameUseCase: IUpdateUserSPNameUseCase
) : BaseViewModel() {
    private val _onLogin = MutableLiveData<Boolean>()
    internal val onLogin: LiveData<Boolean> = _onLogin

    private val _onFailToLogin = MutableLiveData<String>()
    internal val onFailToLogin: LiveData<String> = _onFailToLogin

    internal fun onLogin(email: String, password: String) {
        viewModelScope.launch(dispatcher) {
            val result = onLoginUseCase.login(email, password)
            when {
                result.isSuccess -> downloadNotes()
                result.isFailure -> _onFailToLogin.postValue(result.exceptionOrNull().toString())
            }
        }
    }

    private suspend fun downloadNotes() {
        onDownloadNotesUseCase.download()
        downloadUserInfo()
    }

    private suspend fun downloadUserInfo() {
        val userModel = onDownloadUserInfoUseCase.download()
        updateUserSPName(userModel.name)
    }

    private suspend fun updateUserSPName(name: String) {
        onUpdateUserSPNameUseCase.update(name)
        _onLogin.postValue(true)
    }
}
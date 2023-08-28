package com.notesfinalproject.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.notesfinalproject.models.NotesModel
import com.notesfinalproject.preferences.UserSP
import com.notesfinalproject.presentation.BaseViewModel
import com.notesfinalproject.usecases.notes.IGetNotesUseCase
import com.notesfinalproject.usecases.user.ILogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val userSP: UserSP,
    private val getNotesUseCase: IGetNotesUseCase,
    private val logoutUseCase: ILogoutUseCase
) : BaseViewModel() {
    private val _onGetUserName = MutableLiveData<String>()
    internal val onGetUserName: LiveData<String> = _onGetUserName

    private val _onGetNotes = MutableLiveData<List<NotesModel>>()
    internal val onGetNotes: LiveData<List<NotesModel>> = _onGetNotes

    private val _onLogout = MutableLiveData<Boolean>()
    internal val onLogout: LiveData<Boolean> = _onLogout

    internal fun getUserName() {
        viewModelScope.launch(dispatcher) {
            _onGetUserName.postValue(userSP.getUserName())
        }
    }

    internal fun getNotes() {
        viewModelScope.launch(dispatcher) {
            _onGetNotes.postValue(getNotesUseCase.get())
        }
    }

    internal fun logout() {
        viewModelScope.launch(dispatcher) {
            logoutUseCase.logout()
            _onLogout.postValue(true)
        }
    }
}
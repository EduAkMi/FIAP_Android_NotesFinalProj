package com.notesfinalproject.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.notesfinalproject.presentation.BaseViewModel
import com.notesfinalproject.usecases.login.ICreateAccountAuthUseCase
import com.notesfinalproject.usecases.login.ICreateAccountFirestoreUseCase
import com.notesfinalproject.usecases.user.IUpdateUserSPNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val onCreateAccountAuthUseCase: ICreateAccountAuthUseCase,
    private val onCreateAccountFirestoreUseCase: ICreateAccountFirestoreUseCase,
    private val onUpdateUserSPNameUseCase: IUpdateUserSPNameUseCase
) : BaseViewModel() {
    private val _onCreateAccount = MutableLiveData<Boolean>()
    internal val onCreateAccount: LiveData<Boolean> = _onCreateAccount

    private val _onFailToCreateAccount = MutableLiveData<String>()
    internal val onFailToCreateAccount: LiveData<String> = _onFailToCreateAccount

    internal fun createAccountAuth(name: String, email: String, password: String) {
        viewModelScope.launch(dispatcher) {
            val result = onCreateAccountAuthUseCase.create(name, email, password)
            when {
                result.isSuccess -> createAccountFirestore(
                    result.getOrNull().toString(),
                    name,
                    email
                )

                result.isFailure -> _onFailToCreateAccount.postValue(result.toString())
            }
        }
    }

    private suspend fun createAccountFirestore(id: String, name: String, email: String) {
        val result = onCreateAccountFirestoreUseCase.create(id, name, email)
        when {
            result.isSuccess -> updateUserSPName(name)
            result.isFailure -> _onFailToCreateAccount.postValue(result.getOrNull())
        }
    }

    private suspend fun updateUserSPName(name: String) {
        onUpdateUserSPNameUseCase.update(name)
        _onCreateAccount.postValue(true)
    }
}
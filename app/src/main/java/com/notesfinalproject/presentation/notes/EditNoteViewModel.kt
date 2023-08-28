package com.notesfinalproject.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.notesfinalproject.enums.NoteColors
import com.notesfinalproject.models.NotesModel
import com.notesfinalproject.presentation.BaseViewModel
import com.notesfinalproject.usecases.notes.ICreateNoteUseCase
import com.notesfinalproject.usecases.notes.IDeleteNoteUseCase
import com.notesfinalproject.usecases.notes.IGetNoteUseCase
import com.notesfinalproject.usecases.notes.IUpdateNoteColorUseCase
import com.notesfinalproject.usecases.notes.IUpdateNoteRemoteUseCase
import com.notesfinalproject.usecases.notes.IUpdateNoteTextsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val createNoteUseCase: ICreateNoteUseCase,
    private val getNoteUseCase: IGetNoteUseCase,
    private val updateNoteTextsUseCase: IUpdateNoteTextsUseCase,
    private val updateNoteColorUseCase: IUpdateNoteColorUseCase,
    private val updateNoteRemoteUseCase: IUpdateNoteRemoteUseCase,
    private val deleteNoteUseCase: IDeleteNoteUseCase
) : BaseViewModel() {
    private var noteId = ""
    private val _onCreateNote = MutableLiveData<String>()
    internal val onCreateNote: LiveData<String> = _onCreateNote

    private val _onGetNote = MutableLiveData<NotesModel>()
    internal val onGetNote: LiveData<NotesModel> = _onGetNote

    private val _onDeleteNote = MutableLiveData<Boolean>()
    internal val onDeleteNote: LiveData<Boolean> = _onDeleteNote

    internal fun createNote() {
        viewModelScope.launch(dispatcher) {
            noteId = createNoteUseCase.create()
            _onCreateNote.postValue(noteId)
        }
    }

    internal fun getNote(noteId: String) {
        this.noteId = noteId
        viewModelScope.launch(dispatcher) {
            _onGetNote.postValue(getNoteUseCase.get(noteId))
        }
    }

    internal fun updateNoteTexts(title: String, description: String) {
        viewModelScope.launch(dispatcher) {
            updateNoteTextsUseCase.update(noteId, title, description)
        }
    }

    internal fun updateNoteColor(color: NoteColors) {
        viewModelScope.launch(dispatcher) {
            updateNoteColorUseCase.update(noteId, color)
        }
    }

    internal fun updateNoteRemote() {
        viewModelScope.launch(dispatcher) {
            updateNoteRemoteUseCase.update(noteId)
        }
    }

    internal fun deleteNote() {
        viewModelScope.launch(dispatcher) {
            deleteNoteUseCase.delete(noteId)
            _onDeleteNote.postValue(true)
        }
    }
}
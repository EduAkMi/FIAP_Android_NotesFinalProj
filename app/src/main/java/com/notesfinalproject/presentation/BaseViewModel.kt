package com.notesfinalproject.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel(

) : ViewModel() {
    internal val dispatcher = Dispatchers.IO
}
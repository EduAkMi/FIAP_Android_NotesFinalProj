package com.notesfinalproject.extensions

import android.view.View

object EnableExtensions {
    private const val LOW_OPACITY = 0.5f
    private const val FULL_OPACITY = 1f

    fun View.lowerOpacity() {
        this.alpha = LOW_OPACITY
    }

    fun View.increaseOpacity() {
        this.alpha = FULL_OPACITY
    }
}
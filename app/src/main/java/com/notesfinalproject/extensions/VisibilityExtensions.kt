package com.notesfinalproject.extensions

import android.view.View

object VisibilityExtensions {
    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }
}
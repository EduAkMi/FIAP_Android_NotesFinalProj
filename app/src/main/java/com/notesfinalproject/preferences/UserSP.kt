package com.notesfinalproject.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserSP @Inject constructor(
    @ApplicationContext context: Context
) {
    private val preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    fun getUserName(): String = preferences.getString(KEY_USER_NAME, "") ?: ""
    fun setUserName(name: String) {
        with(preferences.edit()) {
            putString(KEY_USER_NAME, name)
            apply()
        }
    }

    fun clear() {
        with(preferences.edit()) {
            clear()
            apply()
        }
    }

    companion object {
        private const val FILE_NAME = "userSP"
        private const val KEY_USER_NAME = "KEY_USER_NAME"
    }
}
package com.notesfinalproject.extensions

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import com.notesfinalproject.enums.NoteColors
import java.util.Calendar
import java.util.Date

object NoteExtensions {
    fun Date.getElapsedDays(): String {
        val currentDate = Date()
        val currentCalendar = Calendar.getInstance()
        val elapsedCalendar = Calendar.getInstance()
        currentCalendar.time = currentDate
        elapsedCalendar.time = this

        val daysDiff = currentCalendar.get(Calendar.DAY_OF_MONTH) -
                elapsedCalendar.get(Calendar.DAY_OF_MONTH)

        return daysDiff.toString()
    }

    fun MaterialCardView.updateColorCard(color: NoteColors, context: Context) {
        strokeWidth = when (color) {
            NoteColors.NONE -> 4
            else -> 0
        }
        setCardBackgroundColor(
            ContextCompat.getColor(
                context,
                color.id
            )
        )
    }
}
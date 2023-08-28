package com.notesfinalproject.models

import androidx.recyclerview.widget.DiffUtil
import com.notesfinalproject.data.entities.NotesEntity
import com.notesfinalproject.enums.NoteColors
import java.util.Date

data class NotesModel(
    val id: String,
    val userId: String,
    val title: String,
    val description: String,
    val color: NoteColors,
    val lastUpdated: Date
) {
    constructor() : this(
        id = "",
        userId = "",
        title = "",
        description = "",
        color = NoteColors.NONE,
        lastUpdated = Date()
    )
}

internal fun NotesModel.toEntity() = NotesEntity(
    noteId = id,
    userId = userId,
    title = title,
    description = description,
    color = color,
    lastUpdated = lastUpdated,
)

val NotesDiffCallback = object : DiffUtil.ItemCallback<NotesModel>() {
    override fun areItemsTheSame(oldItem: NotesModel, newItem: NotesModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NotesModel, newItem: NotesModel): Boolean {
        return oldItem == newItem
    }
}
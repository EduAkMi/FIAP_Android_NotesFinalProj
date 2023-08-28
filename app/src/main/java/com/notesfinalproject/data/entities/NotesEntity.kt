package com.notesfinalproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.notesfinalproject.enums.NoteColors
import com.notesfinalproject.models.NotesModel
import java.util.Date

@Entity(tableName = NOTES_TABLE_NAME)
data class NotesEntity(
    @ColumnInfo(name = NOTES_NOTE_ID) val noteId: String,
    @ColumnInfo(name = NOTES_USER_ID) val userId: String,
    @ColumnInfo(name = NOTES_TITLE) val title: String,
    @ColumnInfo(name = NOTES_DESCRIPTION) val description: String,
    @ColumnInfo(name = NOTES_COLOR) val color: NoteColors,
    @ColumnInfo(name = NOTES_LAST_UPDATED) val lastUpdated: Date
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}

internal fun NotesEntity.toModel() = NotesModel(
    id = noteId,
    userId = userId,
    title = title,
    description = description,
    color = color,
    lastUpdated = lastUpdated,
)

const val NOTES_TABLE_NAME = "notes"
const val NOTES_NOTE_ID = "noteId"
const val NOTES_USER_ID = "userId"
const val NOTES_TITLE = "title"
const val NOTES_DESCRIPTION = "description"
const val NOTES_COLOR = "color"
const val NOTES_LAST_UPDATED = "lastUpdated"

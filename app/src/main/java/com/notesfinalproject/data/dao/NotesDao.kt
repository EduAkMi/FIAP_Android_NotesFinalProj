package com.notesfinalproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.notesfinalproject.data.entities.NOTES_COLOR
import com.notesfinalproject.data.entities.NOTES_DESCRIPTION
import com.notesfinalproject.data.entities.NOTES_LAST_UPDATED
import com.notesfinalproject.data.entities.NOTES_NOTE_ID
import com.notesfinalproject.data.entities.NOTES_TABLE_NAME
import com.notesfinalproject.data.entities.NOTES_TITLE
import com.notesfinalproject.data.entities.NotesEntity
import com.notesfinalproject.enums.NoteColors
import java.util.Date

@Dao
interface NotesDao {
    @Insert
    fun add(entity: NotesEntity)

    @Query("SELECT * FROM $NOTES_TABLE_NAME")
    fun get(): List<NotesEntity>


    @Query(
        "SELECT * FROM $NOTES_TABLE_NAME WHERE " +
                "$NOTES_NOTE_ID = :id"
    )
    fun getSingle(id: String): NotesEntity

    @Query(
        "UPDATE $NOTES_TABLE_NAME SET " +
                "$NOTES_TITLE = :title, " +
                "$NOTES_DESCRIPTION = :description, " +
                "$NOTES_LAST_UPDATED = :lastUpdated WHERE " +
                "$NOTES_NOTE_ID = :noteId"
    )
    fun updateTexts(
        noteId: String,
        title: String,
        description: String,
        lastUpdated: Date
    )

    @Query(
        "UPDATE $NOTES_TABLE_NAME SET " +
                "$NOTES_COLOR = :color, " +
                "$NOTES_LAST_UPDATED = :lastUpdated WHERE " +
                "$NOTES_NOTE_ID = :noteId"
    )
    fun updateColor(
        noteId: String,
        color: NoteColors,
        lastUpdated: Date
    )

    @Query(
        "DELETE FROM $NOTES_TABLE_NAME WHERE " +
                "$NOTES_NOTE_ID = :id"
    )
    fun delete(id: String)

    @Query("DELETE FROM $NOTES_TABLE_NAME")
    fun clear()
}
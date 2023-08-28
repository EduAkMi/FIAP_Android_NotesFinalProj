package com.notesfinalproject.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.notesfinalproject.databinding.RecNotesBinding
import com.notesfinalproject.models.NotesDiffCallback
import com.notesfinalproject.models.NotesModel

class NotesAdapter(
    private val onSelect: (String) -> Unit
) : ListAdapter<NotesModel, NotesViewHolder>(NotesDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            RecNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(
            note = getItem(position),
            onSelect = onSelect
        )
    }
}
package com.notesfinalproject.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.notesfinalproject.R
import com.notesfinalproject.databinding.RecNotesBinding
import com.notesfinalproject.extensions.NoteExtensions.getElapsedDays
import com.notesfinalproject.extensions.NoteExtensions.updateColorCard
import com.notesfinalproject.models.NotesModel

class NotesViewHolder(
    private val binding: RecNotesBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        note: NotesModel,
        onSelect: (String) -> Unit
    ) = with(binding) {
        txtTitle.text = when (note.title.isEmpty()) {
            true -> binding.root.context.getString(R.string.note_no_title)
            else -> note.title
        }
        val days = note.lastUpdated.getElapsedDays()
        txtDate.text = binding.root.context.resources.getQuantityString(
            R.plurals.note_days_ago,
            days.toInt(),
            days.toInt()
        )
        cardRoot.updateColorCard(note.color, binding.root.context)

        root.setOnClickListener { onSelect(note.id) }
    }
}
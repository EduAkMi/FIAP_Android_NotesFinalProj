package com.notesfinalproject.presentation.bottomdialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.notesfinalproject.R
import com.notesfinalproject.databinding.BottomNoteColorsBinding
import com.notesfinalproject.enums.NoteColors

object BottomNoteColors {
    fun show(
        context: Context,
        onDelete: () -> Unit,
        onSelectColor: (NoteColors) -> Unit
    ) {
        val bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        val binding = BottomNoteColorsBinding.inflate(LayoutInflater.from(context))
        bottomSheetDialog.setContentView(binding.root)
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetDialog.behavior.skipCollapsed = true

        binding.txtDelete.dismissAfterClick(bottomSheetDialog) {
            onDelete()
        }
        binding.imgColorNone.dismissAfterClick(bottomSheetDialog) {
            onSelectColor(NoteColors.NONE)
        }
        binding.imgColorSalmon.dismissAfterClick(bottomSheetDialog) {
            onSelectColor(NoteColors.SALMON)
        }
        binding.imgColorOrange.dismissAfterClick(bottomSheetDialog) {
            onSelectColor(NoteColors.ORANGE)
        }
        binding.imgColorYellow.dismissAfterClick(bottomSheetDialog) {
            onSelectColor(NoteColors.YELLOW)
        }
        binding.imgColorCyan.dismissAfterClick(bottomSheetDialog) {
            onSelectColor(NoteColors.CYAN)
        }
        binding.imgColorGreen.dismissAfterClick(bottomSheetDialog) {
            onSelectColor(NoteColors.GREEN)
        }
        binding.imgColorPink1.dismissAfterClick(bottomSheetDialog) {
            onSelectColor(NoteColors.PINK_1)
        }
        binding.imgColorPurple.dismissAfterClick(bottomSheetDialog) {
            onSelectColor(NoteColors.PURPLE)
        }
        binding.imgColorBlue.dismissAfterClick(bottomSheetDialog) {
            onSelectColor(NoteColors.BLUE)
        }
        binding.imgColorGrey.dismissAfterClick(bottomSheetDialog) {
            onSelectColor(NoteColors.GREY)
        }
        binding.imgColorPink2.dismissAfterClick(bottomSheetDialog) {
            onSelectColor(NoteColors.PINK_2)
        }
        binding.imgColorWhite.dismissAfterClick(bottomSheetDialog) {
            onSelectColor(NoteColors.WHITE)
        }

        bottomSheetDialog.show()
    }

    private fun View.dismissAfterClick(bottomSheetDialog: BottomSheetDialog, action: () -> Unit) {
        setOnClickListener {
            action()
            bottomSheetDialog.dismiss()
        }
    }
}
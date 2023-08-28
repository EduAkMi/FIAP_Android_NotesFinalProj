package com.notesfinalproject.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.notesfinalproject.R
import com.notesfinalproject.databinding.FragmentEditNoteBinding
import com.notesfinalproject.extensions.NoteExtensions.updateColorCard
import com.notesfinalproject.extensions.VisibilityExtensions.gone
import com.notesfinalproject.presentation.bottomdialogs.BottomNoteColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditNoteFragment : Fragment() {
    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!
    private val args: EditNoteFragmentArgs by navArgs()
    private val viewModel: EditNoteViewModel by viewModels()
    private var isDeleted = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (!isDeleted) viewModel.updateNoteRemote()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupArguments()
        setupClickListeners()
        setupObservables()
    }

    private fun setupArguments() {
        when (val noteId = args.noteId) {
            null -> viewModel.createNote()
            else -> viewModel.getNote(noteId)
        }
    }

    private fun setupClickListeners() {
        binding.cardProgress.setOnClickListener { }
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
        binding.cardColor.setOnClickListener { displayBottomDialogColors() }
    }

    private fun setupObservables() {
        viewModel.onCreateNote.observe(viewLifecycleOwner) {
            binding.cardProgress.gone()
            setupTextChangedListeners()
        }
        viewModel.onGetNote.observe(viewLifecycleOwner) { noteModel ->
            binding.cardProgress.gone()
            binding.edtTitle.setText(noteModel.title)
            binding.edtDescription.setText(noteModel.description)
            binding.cardColor.updateColorCard(noteModel.color, requireContext())
            setupTextChangedListeners()
        }
        viewModel.onDeleteNote.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), getString(R.string.note_deleted), Toast.LENGTH_SHORT)
                .show()
            isDeleted = true
            findNavController().popBackStack()
        }
    }

    private fun setupTextChangedListeners() {
        binding.edtTitle.addTextChangedListener {
            viewModel.updateNoteTexts(
                binding.edtTitle.text.toString(),
                binding.edtDescription.text.toString()
            )
        }
        binding.edtDescription.addTextChangedListener {
            viewModel.updateNoteTexts(
                binding.edtTitle.text.toString(),
                binding.edtDescription.text.toString()
            )
        }
    }

    private fun displayBottomDialogColors() {
        BottomNoteColors.show(
            context = requireContext(),
            onDelete = { viewModel.deleteNote() },
            onSelectColor = {
                viewModel.updateNoteColor(it)
                binding.cardColor.updateColorCard(it, requireContext())
            }
        )
    }
}
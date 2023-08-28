package com.notesfinalproject.presentation.notes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.notesfinalproject.R
import com.notesfinalproject.databinding.FragmentNotesBinding
import com.notesfinalproject.models.NotesModel
import com.notesfinalproject.presentation.adapters.NotesAdapter
import com.notesfinalproject.presentation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        setupObservables()

        asyncFunctions()
    }

    private fun setupClickListeners() {
        binding.btnCreate.setOnClickListener {
            goToEditNote(null)
        }
        binding.txtLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun setupObservables() {
        viewModel.onGetUserName.observe(viewLifecycleOwner) { userName ->
            binding.txtTitle.text = getString(R.string.hello_user, userName)
        }
        viewModel.onGetNotes.observe(viewLifecycleOwner) { notes ->
            binding.groupEmpty.visibility = when (notes.isEmpty()) {
                true -> View.VISIBLE
                false -> View.GONE
            }
            setupRecyclerView(notes)
        }
        viewModel.onLogout.observe(viewLifecycleOwner) {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            requireActivity().finish()
            requireActivity().startActivity(intent)
        }
    }

    private fun setupRecyclerView(notes: List<NotesModel>) {
        val adapter = NotesAdapter(
            onSelect = { goToEditNote(it) }
        )
        with(binding.recNotes) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            this.adapter = adapter
        }
        adapter.submitList(notes)
    }

    private fun goToEditNote(noteId: String?) {
        val action = NotesFragmentDirections.actionNotesToCreateNote(
            noteId = noteId
        )
        findNavController().navigate(action)
    }

    private fun asyncFunctions() {
        viewModel.getUserName()
        viewModel.getNotes()
    }
}
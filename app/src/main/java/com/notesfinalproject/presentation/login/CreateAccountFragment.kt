package com.notesfinalproject.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.notesfinalproject.R
import com.notesfinalproject.databinding.FragmentCreateAccountBinding
import com.notesfinalproject.extensions.EnableExtensions.increaseOpacity
import com.notesfinalproject.extensions.EnableExtensions.lowerOpacity
import com.notesfinalproject.extensions.VisibilityExtensions.gone
import com.notesfinalproject.extensions.VisibilityExtensions.visible
import com.notesfinalproject.presentation.notes.NotesActivity
import com.notesfinalproject.utils.LoginRequirements.MINIMUM_LENGTH_NAME
import com.notesfinalproject.utils.LoginRequirements.MINIMUM_LENGTH_PASSWORD
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : Fragment() {
    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CreateAccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTextChangedListeners()
        setupClickListeners()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.onCreateAccount.observe(viewLifecycleOwner) {
            binding.cardProgress.gone()
            val intent = Intent(requireActivity(), NotesActivity::class.java)
            requireActivity().finish()
            requireActivity().startActivity(intent)
        }
        viewModel.onFailToCreateAccount.observe(viewLifecycleOwner) { response ->
            binding.cardProgress.gone()
            Toast.makeText(requireContext(), response.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupClickListeners() {
        binding.cardProgress.setOnClickListener { }
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnCreateAccount.setOnClickListener {
            val verifyFields = verifyFields()
            if (verifyFields.isNotEmpty()) {
                Toast.makeText(requireContext(), verifyFields, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            binding.cardProgress.visible()
            viewModel.createAccountAuth(
                name = binding.edtName.text.toString(),
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString()
            )
        }
    }

    private fun setupTextChangedListeners() {
        binding.edtName.addTextChangedListener {
            toggleCreateAccountOpacityByVerification()
        }
        binding.edtEmail.addTextChangedListener {
            toggleCreateAccountOpacityByVerification()
        }
        binding.edtPassword.addTextChangedListener {
            toggleCreateAccountOpacityByVerification()
        }
        binding.edtPasswordConfirm.addTextChangedListener {
            toggleCreateAccountOpacityByVerification()
        }
    }

    private fun toggleCreateAccountOpacityByVerification() {
        val verifyFields = verifyFields()
        when (verifyFields.isEmpty()) {
            true -> binding.btnCreateAccount.increaseOpacity()
            false -> binding.btnCreateAccount.lowerOpacity()
        }
    }

    private fun verifyFields(): String {
        return when {
            binding.edtName.text.toString().isEmpty() -> getString(R.string.login_name_empty)
            binding.edtEmail.text.toString().isEmpty() -> getString(R.string.login_email_empty)
            binding.edtPassword.text.toString()
                .isEmpty() -> getString(R.string.login_password_empty)

            binding.edtPasswordConfirm.text.toString()
                .isEmpty() -> getString(R.string.login_password_confirm_empty)

            binding.edtPassword.text.toString() != binding.edtPasswordConfirm.text.toString() -> getString(
                R.string.login_passwords_dont_match
            )

            binding.edtName.text.toString().length < MINIMUM_LENGTH_NAME -> getString(
                R.string.login_name_minimum,
                MINIMUM_LENGTH_NAME.toString()
            )

            binding.edtPassword.text.toString().length < MINIMUM_LENGTH_PASSWORD -> getString(
                R.string.login_password_minimum,
                MINIMUM_LENGTH_PASSWORD.toString()
            )

            else -> ""
        }
    }
}
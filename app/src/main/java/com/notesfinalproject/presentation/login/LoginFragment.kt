package com.notesfinalproject.presentation.login

import android.content.Intent
import android.graphics.Paint
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
import com.notesfinalproject.databinding.FragmentLoginBinding
import com.notesfinalproject.extensions.EnableExtensions.increaseOpacity
import com.notesfinalproject.extensions.EnableExtensions.lowerOpacity
import com.notesfinalproject.extensions.VisibilityExtensions.gone
import com.notesfinalproject.extensions.VisibilityExtensions.visible
import com.notesfinalproject.presentation.notes.NotesActivity
import com.notesfinalproject.utils.LoginRequirements.MINIMUM_LENGTH_PASSWORD
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTextPaintFlags()
        setupTextChangedListeners()
        setupClickListeners()
        setupObservables()
    }

    private fun setupTextPaintFlags() {
        binding.txtCreateAccountAction.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun setupObservables() {
        viewModel.onLogin.observe(viewLifecycleOwner) {
            binding.cardProgress.gone()
            val intent = Intent(requireActivity(), NotesActivity::class.java)
            requireActivity().finish()
            requireActivity().startActivity(intent)
        }
        viewModel.onFailToLogin.observe(viewLifecycleOwner) { response ->
            binding.cardProgress.gone()
            Toast.makeText(requireContext(), response.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupClickListeners() {
        binding.txtCreateAccountAction.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginToCreateAccount()
            findNavController().navigate(action)
        }
        binding.btnLogin.setOnClickListener {
            val verifyFields = verifyFields()
            if (verifyFields.isNotEmpty()) {
                Toast.makeText(requireContext(), verifyFields, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            binding.cardProgress.visible()
            viewModel.onLogin(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString()
            )
        }
    }

    private fun setupTextChangedListeners() {
        binding.edtEmail.addTextChangedListener {
            toggleCreateAccountOpacityByVerification()
        }
        binding.edtPassword.addTextChangedListener {
            toggleCreateAccountOpacityByVerification()
        }
    }

    private fun toggleCreateAccountOpacityByVerification() {
        val verifyFields = verifyFields()
        when (verifyFields.isEmpty()) {
            true -> binding.btnLogin.increaseOpacity()
            false -> binding.btnLogin.lowerOpacity()
        }
    }

    private fun verifyFields(): String {
        return when {
            binding.edtEmail.text.toString().isEmpty() -> getString(R.string.login_email_empty)
            binding.edtPassword.text.toString()
                .isEmpty() -> getString(R.string.login_password_empty)

            binding.edtPassword.text.toString().length < MINIMUM_LENGTH_PASSWORD -> getString(
                R.string.login_password_minimum,
                MINIMUM_LENGTH_PASSWORD.toString()
            )

            else -> ""
        }
    }
}
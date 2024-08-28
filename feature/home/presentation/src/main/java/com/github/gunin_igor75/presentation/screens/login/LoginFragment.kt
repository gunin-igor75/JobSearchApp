package com.github.gunin_igor75.presentation.screens.login

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.common.base.base.BaseFragment
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.databinding.FragmentLoginBinding
import com.github.gunin_igor75.presentation.utils.watchers.TextWatcherInputEmail
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding(R.id.loginFragment)

    private val vm: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditTextEmail()
        setupButtonContinue()
        launchFragmentVerification()
        setupErrorIconTextInput()
    }

    private fun setupErrorIconTextInput() {
        with(binding.includeWorker) {
            textInputLayoutEmail.setErrorIconOnClickListener {
                textInputEditTextEmail.setText(R.string.empty)
            }
        }
    }

    private fun launchFragmentVerification() {
        binding.includeWorker.buttonContinue.setOnClickListener {
            val email = binding.includeWorker.textInputEditTextEmail.text.toString()
            val isValidate = vm.isValidateEmail(email)
            if (isValidate) {
                val active =
                    LoginFragmentDirections.actionLoginFragment2ToVerificationFragment2(email)
                findNavController().navigate(active)
            } else {
                binding.includeWorker.textInputLayoutEmail.error = getString(R.string.error_email)
            }
        }
    }

    private fun setupButtonContinue() {
        lifecycleScope.launch {
            vm.isActiveButtonState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                binding.includeWorker.buttonContinue.isEnabled = it
            }
        }
    }

    private fun setupEditTextEmail() {
        val borderStrokeColor =
            ContextCompat.getColor(requireContext(), com.github.gunin_igor75.common.R.color.gray2)
        val textWatcher = TextWatcherInputEmail(
            textInputLayout = binding.includeWorker.textInputLayoutEmail,
            color = borderStrokeColor
        ) { text ->
            vm.setStateActiveButton(text)
        }
        binding.includeWorker.textInputEditTextEmail.addTextChangedListener(textWatcher)
    }
}
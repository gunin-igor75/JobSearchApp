package com.github.gunin_igor75.presentation.screens.verification

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.common.base.base.BaseFragment
import com.github.gunin_igor75.common.base.utils.Constants.Companion.ALPHA_ACTIVE
import com.github.gunin_igor75.common.base.utils.Constants.Companion.ALPHA_NOT_ACTIVE
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.databinding.FragmentVerificationBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class VerificationFragment : BaseFragment(R.layout.fragment_verification) {

    private val binding: FragmentVerificationBinding by  viewBinding(R.id.verificationFragment)

    private val vm: VerificationViewModel by viewModel()

    private val args by navArgs<VerificationFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEmailTitle()
        setupColorHintEditText()
        settingActionUponChangeEditText()
        setupModeActiveButtonConfirm()
        launchHomeFragment()
    }

    private fun setupEmailTitle() {
        val text = String.format(getString(R.string.send_email), args.email)
        binding.textViewSendEmail.text = text
    }

    private fun setupModeActiveButtonConfirm() {
        lifecycleScope.launch {
            vm.isActiveButtonConfirm.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                val alpha = if (it) ALPHA_ACTIVE else ALPHA_NOT_ACTIVE
                binding.buttonConfirm.isEnabled = it
                binding.buttonConfirm.alpha = alpha
            }
        }
    }

    private fun settingActionUponChangeEditText() {
        with(binding) {
            editTextCode1.requestFocus()
            checkValueEditText(editTextCode1) {
                val isValid = vm.setStateActiveButtonEdit1(it)
                if (isValid) editTextCode2.requestFocus()
            }
            checkValueEditText(editTextCode2) {
                val isValid = vm.setStateActiveButtonEdit2(it)
                if (isValid) editTextCode3.requestFocus()
            }
            checkValueEditText(editTextCode3) {
                val isValid = vm.setStateActiveButtonEdit3(it)
                if (isValid) editTextCode4.requestFocus()
            }
            checkValueEditText(editTextCode4) { vm.setStateActiveButtonEdit4(it) }
        }
    }

    private fun checkValueEditText(
        editText: EditText,
        action: (String) -> Unit,
    ) {
        editText.doOnTextChanged { text, _, _, _ ->
            action(text.toString())
        }
    }

    private fun setupColorHintEditText() {
        with(binding) {
            changeColorHintEditText(editTextCode1)
            changeColorHintEditText(editTextCode2)
            changeColorHintEditText(editTextCode3)
            changeColorHintEditText(editTextCode4)
        }
    }


    private fun changeColorHintEditText(editText: EditText) {
        editText.setOnFocusChangeListener { v, hasFocus ->
            val colorHasFocus = ContextCompat.getColor(
                requireContext(),
                com.github.gunin_igor75.common.R.color.transparent
            )
            val colorNotHasFocus = ContextCompat.getColor(
                requireContext(),
                com.github.gunin_igor75.common.R.color.gray3
            )
            if (hasFocus) {
                editText.setHintTextColor(colorHasFocus)
            } else {
                val text = editText.text.toString().trim()
                if (text.isEmpty()) {
                    editText.setHintTextColor(colorNotHasFocus)
                }
            }
        }
    }

    private fun launchHomeFragment() {
        binding.buttonConfirm.setOnClickListener {
            findNavController().navigate(R.id.action_verificationFragment2_to_homeFragment2)
        }
    }
}

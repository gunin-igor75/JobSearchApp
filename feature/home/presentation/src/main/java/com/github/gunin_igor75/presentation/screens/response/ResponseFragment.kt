package com.github.gunin_igor75.presentation.screens.response

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.databinding.FragmentResponseBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ResponseFragment : BottomSheetDialogFragment(R.layout.fragment_response) {

    private val binding: FragmentResponseBinding by viewBinding(R.id.responseFragmentId)

    private val args by navArgs<ResponseFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVacancyTitle()
        setupEditTextButton()
        onClickCoverLetter()
        onClickButtonResponse()
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return BottomSheetDialog(requireContext(), com.github.gunin_igor75.common.R.style.MyTransparentBottomSheetDialogTheme)
//    }

    private fun setupVacancyTitle() {
        binding.textViewVacancy.text = args.uiContainer.vacancyName
    }

    private fun setupEditTextButton() {
        val uiContainer = args.uiContainer
        val question = uiContainer.question
        if (question == null) {
            binding.editText.visibility = View.GONE
            binding.textViewAddLetter.visibility = View.VISIBLE
        } else {
            binding.editText.visibility = View.VISIBLE
            binding.textViewAddLetter.visibility = View.GONE
            binding.editText.setText(question)
            binding.editText.requestFocus()
        }
    }

    private fun onClickCoverLetter() {
        binding.textViewAddLetter.setOnClickListener {
            binding.textViewAddLetter.visibility = View.GONE
            binding.editText.visibility = View.VISIBLE
        }
    }

    private fun onClickButtonResponse() {
        binding.buttonRespond.setOnClickListener {
            dismiss()
        }
    }
}
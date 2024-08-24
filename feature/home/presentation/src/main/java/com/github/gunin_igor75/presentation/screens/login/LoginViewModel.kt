package com.github.gunin_igor75.presentation.screens.login

import androidx.lifecycle.ViewModel
import com.github.gunin_igor75.common.base.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {

    private val _isActiveButtonState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isActiveButtonState: StateFlow<Boolean> = _isActiveButtonState.asStateFlow()


    fun setStateActiveButton(text: String) {
        _isActiveButtonState.value = text.isNotBlank()
    }

    fun isValidateEmail(email: String): Boolean{
        val regex = Constants.PATTERN_EMAIL.toRegex()
        return regex.matches(email)
    }
}
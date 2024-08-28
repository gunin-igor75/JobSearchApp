package com.github.gunin_igor75.presentation.screens.verification

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class VerificationViewModel : ViewModel() {

    private val _isActiveStateEdit1: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isActiveStateEdit2: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isActiveStateEdit3: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isActiveStateEdit4: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val isActiveButtonConfirm = combine(
        _isActiveStateEdit1,
        _isActiveStateEdit2,
        _isActiveStateEdit3,
        _isActiveStateEdit4,
    ) { array -> array.all { it } }


    fun setStateActiveButtonEdit1(text: String): Boolean {
        val isValid = text.isNotEmpty()
        _isActiveStateEdit1.value = isValid
        return isValid
    }

    fun setStateActiveButtonEdit2(text: String): Boolean {
        val isValid = text.isNotEmpty()
        _isActiveStateEdit2.value = isValid
        return isValid
    }

    fun setStateActiveButtonEdit3(text: String): Boolean {
        val isValid = text.isNotEmpty()
        _isActiveStateEdit3.value = isValid
        return isValid
    }

    fun setStateActiveButtonEdit4(text: String): Boolean {
        val isValid = text.isNotEmpty()
        _isActiveStateEdit4.value = isValid
        return isValid
    }
}
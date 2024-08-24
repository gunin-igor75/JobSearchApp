package com.github.gunin_igor75.presentation.utils.watchers

import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.ColorInt
import com.google.android.material.textfield.TextInputLayout

class TextWatcherInputEmail(
    private val textInputLayout: TextInputLayout,
    @ColorInt private val color: Int,
    private val action: (String) -> Unit,
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        textInputLayout.error = null
        textInputLayout.boxStrokeColor =  color
    }

    override fun afterTextChanged(s: Editable?) {
        val text = s?.toString()?.trim() ?: ""
        action(text)
    }
}
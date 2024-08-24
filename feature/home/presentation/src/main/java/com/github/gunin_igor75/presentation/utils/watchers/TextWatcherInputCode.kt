package com.github.gunin_igor75.presentation.utils.watchers

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.annotation.ColorInt

class TextWatcherInputCode(
    private val textEditText: EditText,
    @ColorInt private val color: Int
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        val text = s?.toString()?.trim() ?: ""
        textEditText.setHintTextColor(color)
    }
}
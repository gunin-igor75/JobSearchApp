package com.github.gunin_igor75.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiContainer(
    val vacancyName: String,
    val question: String? = null
) : Parcelable

package com.github.gunin_igor75.presentation.model

import androidx.annotation.DrawableRes
import com.github.gunin_igor75.common.base.model.ListItem

data class UiOffer(
    override val lisItemId: String,
    val id: String,
    val title: String,
    val link: String,
    val buttonText: String?,
    @DrawableRes val icon: Int?
): ListItem

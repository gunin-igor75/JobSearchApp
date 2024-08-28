package com.github.gunin_igor75.common.base.model

import androidx.annotation.DrawableRes

data class UiOffer(
    override val listItemId: String,
    val id: String?,
    val title: String,
    val link: String,
    val buttonText: String?,
    @DrawableRes val icon: Int?
): ListItem

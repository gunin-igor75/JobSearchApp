package com.github.gunin_igor75.presentation.model

import com.github.gunin_igor75.common.base.model.ListItem

data class UiQuestion(
    override val listItemId: String,
    val text: String,
) : ListItem

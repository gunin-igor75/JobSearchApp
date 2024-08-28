package com.github.gunin_igor75.presentation.model

import com.github.gunin_igor75.common.base.model.ListItem

data class HomeStateHolder(
    val data: List<ListItem>? = null,
    val loading: Boolean = false
)

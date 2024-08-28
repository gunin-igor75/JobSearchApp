package com.github.gunin_igor75.common.base.model

data class UiVacancyFavorite(
    override val listItemId: String,
    val lookingNumber: Int? = null,
    val isFavorite: Boolean,
    val title: String,
    val town: String,
    val company: String,
    val previewText: String,
    val publishedDate: String,
): ListItem

package com.github.gunin_igor75.repo.mappers

import com.github.gunin_igor75.common.base.model.FavoriteVacancyModel
import com.github.gunin_igor75.common.base.model.UiVacancy

fun UiVacancy.toFavoriteVacancyModel() = FavoriteVacancyModel(
    id = listItemId,
    lookingNumber = lookingNumber,
    isFavorite = isFavorite,
    title = title,
    town = town,
    company = company,
    previewText = experiens,
    publishedDate = publishedDate,
)
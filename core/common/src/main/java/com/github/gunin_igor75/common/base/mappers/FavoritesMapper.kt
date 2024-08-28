package com.github.gunin_igor75.common.base.mappers

import com.github.gunin_igor75.common.base.model.FavoriteVacancyModel
import com.github.gunin_igor75.common.base.model.UiVacancyFavorite


fun List<FavoriteVacancyModel>.toUiVacanciesFavorites() = map { it.toUiVacancyFavorite() }

private fun FavoriteVacancyModel.toUiVacancyFavorite() = UiVacancyFavorite(
    listItemId = id,
    lookingNumber = lookingNumber,
    isFavorite = isFavorite,
    title = title,
    town = town,
    company = company,
    previewText = previewText,
    publishedDate = publishedDate,
)

fun UiVacancyFavorite.toFavoriteVacancyModel() = FavoriteVacancyModel(
    id = listItemId,
    lookingNumber = lookingNumber,
    isFavorite = isFavorite,
    title = title,
    town = town,
    company = company,
    previewText = previewText,
    publishedDate = publishedDate,
)
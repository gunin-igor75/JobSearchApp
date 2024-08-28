package com.github.gunin_igor75.repo.mappers

import com.github.gunin_igor75.common.base.model.FavoriteVacancyModel
import com.github.gunin_igor75.common.base.model.UiVacancy
import com.github.gunin_igor75.common.base.model.VacanciesModel
import com.github.gunin_igor75.local.model.VacanciesDb

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

fun FavoriteVacancyModel.toVacancyDb() = VacanciesDb(
    id,
    lookingNumber,
    isFavorite,
    title,
    town,
    company,
    previewText,
    publishedDate,
)
fun VacanciesDb.toFavoriteVacancyModel() = FavoriteVacancyModel(
    id,
    lookingNumber,
    isFavorite,
    title,
    town,
    company,
    previewText,
    publishedDate
)
fun VacanciesModel.toFavoriteVacancyModel() = FavoriteVacancyModel(
    id,
    lookingNumber,
    isFavorite,
    title,
    addressModel.town,
    company,
    experience.previewText,
    publishedDate
)
fun List<VacanciesDb>.toFavoritesVacanciesModels() = map { it.toFavoriteVacancyModel() }
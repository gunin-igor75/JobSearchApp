package com.github.gunin_igor75.data.mappers

import com.github.gunin_igor75.domain.model.FavoriteVacancyModel
import com.github.gunin_igor75.local.model.VacanciesDb

fun FavoriteVacancyModel.toVacancyDb() = VacanciesDb(id, lookingNumber, isFavorite, title, town, company, previewText, publishedDate,)
fun VacanciesDb.toFavoriteVacancyModel() = FavoriteVacancyModel(id, lookingNumber, isFavorite, title, town, company, previewText, publishedDate)
fun List<VacanciesDb>.toFavoritesVacanciesModels() = map { it.toFavoriteVacancyModel() }
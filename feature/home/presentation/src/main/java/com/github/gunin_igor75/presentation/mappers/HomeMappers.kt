package com.github.gunin_igor75.presentation.mappers

import com.github.gunin_igor75.common.R
import com.github.gunin_igor75.common.base.model.AddressModel
import com.github.gunin_igor75.common.base.model.FavoriteVacancyModel
import com.github.gunin_igor75.common.base.model.VacanciesModel
import com.github.gunin_igor75.common.base.utils.Constants.Companion.OFFER_ID_1
import com.github.gunin_igor75.common.base.utils.Constants.Companion.OFFER_ID_2
import com.github.gunin_igor75.common.base.utils.Constants.Companion.OFFER_ID_3
import com.github.gunin_igor75.domain.model.OfferModel
import com.github.gunin_igor75.presentation.model.UiOffer
import com.github.gunin_igor75.presentation.model.UiVacancy


fun List<OfferModel>.toUiOffers() = map { it.toUiOffer() }

private fun OfferModel.toUiOffer() = UiOffer(
    listItemId = listItemId,
    id = id,
    title = title.trim(),
    link = link,
    buttonText = buttonText,
    icon = getDrawableResId(id)
)

fun List<VacanciesModel>.toUiVacancies() = map { it.toUiVacancy() }

fun VacanciesModel.toUiVacancy() = UiVacancy(
    listItemId = id,
    title = title,
    salary = salary.full,
    experiens = experience.text,
    schedules = convertSchedules(schedules),
    appliedNumber = appliedNumber,
    lookingNumber = lookingNumber,
    address = convertAddress(addressModel),
    description = description?.replace("\\n+".toRegex(), "\n"),
    responsibilities = responsibilities.replace("\\n+".toRegex(), "\n"),
    questions = questions,
    isFavorite = isFavorite,
    town = addressModel.town,
    company = company,
    publishedDate = publishedDate
)

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

private fun getDrawableResId(id: String?) =
    when (id) {
        OFFER_ID_1 -> R.drawable.ic_offer_avatar
        OFFER_ID_2 -> R.drawable.ic_offer_star
        OFFER_ID_3 -> R.drawable.ic_offer_note
        else -> null
    }

private fun convertSchedules(list: List<String>) =
    list.joinToString(",").replaceFirstChar { it.uppercase() }

private fun convertAddress(addressModel: AddressModel) =
    "${addressModel.town}, ${addressModel.street}, ${addressModel.house}"

fun main() {

}
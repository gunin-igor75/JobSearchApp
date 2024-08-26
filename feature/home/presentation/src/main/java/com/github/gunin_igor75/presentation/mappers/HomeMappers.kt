package com.github.gunin_igor75.presentation.mappers

import com.github.gunin_igor75.common.R
import com.github.gunin_igor75.common.base.utils.Constants.Companion.OFFER_ID_1
import com.github.gunin_igor75.common.base.utils.Constants.Companion.OFFER_ID_2
import com.github.gunin_igor75.common.base.utils.Constants.Companion.OFFER_ID_3
import com.github.gunin_igor75.common.base.utils.Constants.Companion.PATTERN_DATE_IN
import com.github.gunin_igor75.common.base.utils.Constants.Companion.PATTERN_DATE_OUT
import com.github.gunin_igor75.common.base.utils.Constants.Companion.PREFIX_DATE
import com.github.gunin_igor75.domain.model.AddressModel
import com.github.gunin_igor75.domain.model.FavoriteVacancyModel
import com.github.gunin_igor75.domain.model.OfferModel
import com.github.gunin_igor75.domain.model.VacanciesModel
import com.github.gunin_igor75.presentation.model.UiOffer
import com.github.gunin_igor75.presentation.model.UiVacancy
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID


fun List<OfferModel>.toUiOffers() = map { it.toUiOffer() }

private fun OfferModel.toUiOffer() = UiOffer(
    listItemId = UUID.randomUUID().toString(),
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
    previewText = experience.previewText,
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
    publishedDate = convertDate(publishedDate)
)

fun UiVacancy.toFavoriteVacancyModel() = FavoriteVacancyModel(
    id = listItemId,
    lookingNumber = lookingNumber,
    isFavorite = isFavorite,
    title = title,
    town = town,
    company = company,
    previewText = previewText,
    publishedDate = publishedDate,
)

private fun getDrawableResId(id: String?) =
    when (id) {
        OFFER_ID_1 -> R.drawable.ic_offer_avatar
        OFFER_ID_2 -> R.drawable.ic_offer_star
        OFFER_ID_3 -> R.drawable.ic_offer_note
        else -> null
    }

private fun convertDate(textDate: String): String {
    val formatIn = SimpleDateFormat(PATTERN_DATE_IN, Locale.getDefault())
    val date = formatIn.parse(textDate) ?: throw IllegalStateException("$textDate is null")
    val formatOut = SimpleDateFormat(PATTERN_DATE_OUT, Locale.getDefault())
    return "$PREFIX_DATE ${formatOut.format(date)}"
}

private fun convertSchedules(list: List<String>) =
    list.joinToString(",").replaceFirstChar { it.uppercase() }

private fun convertAddress(addressModel: AddressModel) =
    "${addressModel.town}, ${addressModel.street}, ${addressModel.house}"

fun main() {

}
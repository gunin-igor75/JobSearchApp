package com.github.gunin_igor75.presentation.mappers

import com.github.gunin_igor75.common.R
import com.github.gunin_igor75.domain.model.OfferModel
import com.github.gunin_igor75.presentation.model.UiOffer
import java.util.UUID


fun List<OfferModel>.toUiOffers() = map { it.toUiOffer() }

private fun OfferModel.toUiOffer() = UiOffer(
    lisItemId = UUID.randomUUID().toString(),
    id = id,
    title = title,
    link = link,
    buttonText = buttonText,
    icon = getDrawableResId(id)
)

private fun getDrawableResId(id: String) =
    when (id) {
        "near_vacancies" -> R.drawable.ic_offer_avatar
        "level_up_resume" -> R.drawable.ic_offer_star
        "temporary_job" -> R.drawable.ic_offer_note
        else -> null
    }

package com.github.gunin_igor75.domain.model

data class OfferModel(
    val listItemId: String,
    val id: String? = null,
    val title: String,
    val link: String,
    val buttonText: String? = null
)

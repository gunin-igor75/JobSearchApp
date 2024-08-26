package com.github.gunin_igor75.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.github.gunin_igor75.domain.usecase.GetOffersUseCase
import com.github.gunin_igor75.domain.usecase.GetVacanciesStateUseCase
import com.github.gunin_igor75.presentation.mappers.toUiOffers
import com.github.gunin_igor75.presentation.model.UiOffer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeViewModel(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesStateUseCase: GetVacanciesStateUseCase,
) : ViewModel() {

    fun getOffers(): Flow<List<UiOffer>?> = getOffersUseCase()
        .map { it.data?.toUiOffers() }

}
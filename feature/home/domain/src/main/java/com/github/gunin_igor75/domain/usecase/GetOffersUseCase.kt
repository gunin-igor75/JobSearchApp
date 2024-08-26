package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.common.base.model.DataResult
import com.github.gunin_igor75.domain.model.OfferModel
import com.github.gunin_igor75.domain.repository.MainRepository
import kotlinx.coroutines.flow.StateFlow

class GetOffersUseCase(
    private val repository: MainRepository<DataResult<List<OfferModel>>>
) {
    operator fun invoke(): StateFlow<DataResult<List<OfferModel>>> = repository.getOffers()
}
package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.common.base.model.DataResult
import com.github.gunin_igor75.domain.model.VacanciesModel
import com.github.gunin_igor75.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class GetVacanciesStateUseCase(
    private val repository: MainRepository<DataResult<List<VacanciesModel>>>
) {
    operator fun invoke(): Flow<DataResult<List<VacanciesModel>>> = repository.getVacanciesState()
}
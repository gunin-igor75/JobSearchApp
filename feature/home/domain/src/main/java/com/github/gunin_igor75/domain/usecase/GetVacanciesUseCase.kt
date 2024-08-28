package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.common.base.model.VacanciesModel
import com.github.gunin_igor75.domain.repository.MainRepository

class GetVacanciesUseCase(
    private val repository: MainRepository<List<VacanciesModel>>,
) {
    operator fun invoke() = repository.getVacancies()
}
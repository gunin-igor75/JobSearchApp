package com.github.gunin_igor75.domain.repository

import com.github.gunin_igor75.common.base.model.VacanciesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MainRepository<out T> {

    fun getOffers(): StateFlow<T>
    fun getVacanciesState(): Flow<T>
    fun getVacancies(): Flow<List<VacanciesModel>>
}
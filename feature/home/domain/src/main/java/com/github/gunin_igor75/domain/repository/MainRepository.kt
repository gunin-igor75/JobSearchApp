package com.github.gunin_igor75.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MainRepository<out T> {

    fun getOffers(): StateFlow<T>

    fun getVacanciesState(): Flow<T>
}
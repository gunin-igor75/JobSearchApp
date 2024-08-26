package com.github.gunin_igor75.data.repository

import android.content.Context
import com.github.gunin_igor75.common.base.model.DataResult
import com.github.gunin_igor75.common.base.utils.Constants.Companion.API_INTERNET_MESSAGE
import com.github.gunin_igor75.common.base.utils.Utils
import com.github.gunin_igor75.data.mappers.toOffersModels
import com.github.gunin_igor75.data.mappers.toVacanciesModels
import com.github.gunin_igor75.domain.model.OfferModel
import com.github.gunin_igor75.domain.model.VacanciesModel
import com.github.gunin_igor75.domain.repository.FavoriteRepository
import com.github.gunin_igor75.domain.repository.MainRepository
import com.github.gunin_igor75.network.NetworkSource
import com.github.gunin_igor75.network.dto.DataContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MainRepositoryImpl(
    private val context: Context,
    private val repoFavorite: FavoriteRepository,
    private val networkSource: NetworkSource<DataContainer>,
    coroutineScope: CoroutineScope,
): MainRepository<DataResult<*>> {

    init {
        coroutineScope.launch(Dispatchers.IO) {
            getData()
        }
    }

    private val _offers: MutableStateFlow<DataResult<List<OfferModel>>> =
        MutableStateFlow(DataResult.Initial())

    private val _vacancies: MutableStateFlow<DataResult<List<VacanciesModel>>> =
        MutableStateFlow(DataResult.Initial())


    override fun getOffers(): StateFlow<DataResult<List<OfferModel>>> = _offers.asStateFlow()

    override fun getVacanciesState(): Flow<DataResult<List<VacanciesModel>>> =
        _vacancies.combine(repoFavorite.getFavoritesVacancies()) { state, favorites ->
            if (state is DataResult.Success) {
                val vacancies = state.data?.map { vac ->
                    if (favorites.any { it.id == vac.id }) {
                        vac.copy(isFavorite = true)
                    } else {
                        vac
                    }
                }
                DataResult.Success(vacancies)
            } else {
                state
            }
        }

    private suspend fun getData() {
        val isInternetConnected = Utils.hasInternetConnection(context)
        if (isInternetConnected) {
            _vacancies.value = DataResult.Loading()
            try {
                val response = networkSource.fetchData()
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        val offers = it.offers?.toOffersModels()
                        _offers.value = DataResult.Success(offers)
                        val vacancies = it.vacancies.toVacanciesModels()
                        _vacancies.value = DataResult.Success(vacancies)
                    }
                } else {
                    _vacancies.value = DataResult.Error(response.message())
                }
            } catch (e: Exception) {
                _vacancies.value = DataResult.Error(e.toString())
            }
        } else {
            _vacancies.value = DataResult.Error(API_INTERNET_MESSAGE)
        }
    }

    companion object {
        private const val TAG = "MainRepository"
    }
}
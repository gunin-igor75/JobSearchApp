package com.github.gunin_igor75.data.repository

import android.content.Context
import com.github.gunin_igor75.common.base.model.DataResult
import com.github.gunin_igor75.common.base.model.OfferModel
import com.github.gunin_igor75.common.base.model.VacanciesModel
import com.github.gunin_igor75.common.base.utils.Constants.Companion.API_INTERNET_MESSAGE
import com.github.gunin_igor75.common.base.utils.Utils
import com.github.gunin_igor75.data.mappers.toOffersModels
import com.github.gunin_igor75.data.mappers.toVacanciesModels
import com.github.gunin_igor75.domain.repository.MainRepository
import com.github.gunin_igor75.network.NetworkSource
import com.github.gunin_igor75.network.dto.DataContainer
import com.github.gunin_igor75.repo.domain.repository.FavoriteRepository
import com.github.gunin_igor75.repo.mappers.toFavoriteVacancyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
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

    private var isVisited: Boolean = false

    private val _offers: MutableStateFlow<DataResult<List<OfferModel>>> =
        MutableStateFlow(DataResult.Initial())

    private val _vacanciesState: MutableStateFlow<DataResult<List<VacanciesModel>>> =
        MutableStateFlow(DataResult.Initial())

    private val _eventChange = MutableSharedFlow<Unit>(replay = 1)

    private var _vacancies: List<VacanciesModel> = listOf()

    private val vacancies
        get() = _vacancies.toList()

    override fun getOffers(): StateFlow<DataResult<List<OfferModel>>> = _offers.asStateFlow()

    override fun getVacanciesState(): Flow<DataResult<List<VacanciesModel>>> =
        _vacanciesState.combine(repoFavorite.getFavoritesVacancies()) { state, favorites ->
            if (state is DataResult.Success) {
                if (!isVisited) {
                    val vacancies = state.data?.map { vac ->
                        if (vac.isFavorite) repoFavorite.addFavorite(vac.toFavoriteVacancyModel())
                        if (favorites.any { it.id == vac.id }) {
                            vac.copy(isFavorite = true)
                        } else {
                            vac
                        }
                    }
                    isVisited = true
                    _vacancies = vacancies ?: listOf()
                } else {
                    val favIds = favorites.map { it.id }.toSet()
                    _vacancies = _vacancies.map { vac ->
                        if (favIds.contains(vac.id)) {
                            vac.copy(isFavorite = true)
                        } else {
                            vac.copy(isFavorite = false)
                        }
                    }
                }
                _eventChange.emit(Unit)
                DataResult.Success(vacancies)
            } else {
                state
            }
        }

    override fun getVacancies(): Flow<List<VacanciesModel>> = flow {
        _eventChange.onStart { emit(Unit) }
            .collect {
                emit(vacancies)
            }
    }

    private suspend fun getData() {
        val isInternetConnected = Utils.hasInternetConnection(context)
        if (isInternetConnected) {
            _vacanciesState.value = DataResult.Loading()
            try {
                val response = networkSource.fetchData()
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        val offers = it.offers?.toOffersModels()
                        _offers.value = DataResult.Success(offers)
                        val vacancies = it.vacancies.toVacanciesModels()
                        _vacanciesState.value = DataResult.Success(vacancies)
                    }
                } else {
                    _vacanciesState.value = DataResult.Error(response.message())
                }
            } catch (e: Exception) {
                _vacanciesState.value = DataResult.Error(e.toString())
            }
        } else {
            _vacanciesState.value = DataResult.Error(API_INTERNET_MESSAGE)
        }
    }
}
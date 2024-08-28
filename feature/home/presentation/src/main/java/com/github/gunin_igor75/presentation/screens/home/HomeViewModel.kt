package com.github.gunin_igor75.presentation.screens.home

import androidx.lifecycle.viewModelScope
import com.github.gunin_igor75.common.base.model.DataResult
import com.github.gunin_igor75.domain.usecase.GetOffersUseCase
import com.github.gunin_igor75.domain.usecase.GetVacanciesStateUseCase
import com.github.gunin_igor75.presentation.base.BaseFavoriteViewModel
import com.github.gunin_igor75.presentation.mappers.toUiOffers
import com.github.gunin_igor75.presentation.mappers.toUiVacancies
import com.github.gunin_igor75.presentation.model.HomeStateHolder
import com.github.gunin_igor75.presentation.model.UiOffer
import com.github.gunin_igor75.repo.domain.usecase.AddFavoriteUseCase
import com.github.gunin_igor75.repo.domain.usecase.RemoveFromFavoritesUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesStateUseCase: GetVacanciesStateUseCase,
    addFavoriteUseCase: AddFavoriteUseCase,
    removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
) : BaseFavoriteViewModel(addFavoriteUseCase, removeFromFavoritesUseCase) {

    private val _error: Channel<Boolean> = Channel()
    val error: Flow<Boolean> = _error.receiveAsFlow()

    private val _vacanciesState = MutableStateFlow(HomeStateHolder())
    val vacanciesState: StateFlow<HomeStateHolder> = _vacanciesState.asStateFlow()

    fun getOffers(): Flow<List<UiOffer>?> = getOffersUseCase()
        .map { it.data?.toUiOffers() }

    fun getVacanciesState() {
        viewModelScope.launch {
            getVacanciesStateUseCase().onEach {dataResult ->
                when(dataResult){
                    is DataResult.Error -> {
                        _error.send(true)
                    }
                    is DataResult.Loading -> {
                        _vacanciesState.value = HomeStateHolder(loading = true)
                    }
                    is DataResult.Success -> {
                        _vacanciesState.value = HomeStateHolder(data = dataResult.data?.toUiVacancies())
                    }
                    is DataResult.Initial -> {}
                }
            }.launchIn(viewModelScope)
        }
    }
}
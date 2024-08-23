package com.github.gunin_igor75.data.repository

import android.content.Context
import com.github.gunin_igor75.common.base.utils.Constants.Companion.API_INTERNET_MESSAGE
import com.github.gunin_igor75.common.base.utils.Utils
import com.github.gunin_igor75.data.mappers.toOffersModels
import com.github.gunin_igor75.data.mappers.toVacanciesModels
import com.github.gunin_igor75.data.model.DataResult
import com.github.gunin_igor75.domain.model.OfferModel
import com.github.gunin_igor75.domain.model.VacanciesModel
import com.github.gunin_igor75.network.NetworkSource
import com.github.gunin_igor75.network.dto.DataContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

class MainRepositoryImpl(
    private val context: Context,
    private val networkSource: NetworkSource<DataContainer>,
    coroutineScope: CoroutineScope,
) {

    private val _offers: MutableStateFlow<DataResult<List<OfferModel>>> = MutableStateFlow(DataResult.Initial())

    fun getOffers() = _offers.asStateFlow()




//    fun getOffers(): Flow<List<OfferModel>?> = _dataContainer
//        .filter { it is DataResult.Success}
//        .map { it.data?.offers?.toOffersModels() }

//    fun getVacancies(): Flow<DataResult<List<VacanciesModel>>> = _dataContainer
//        .map {
//            if (it is DataResult.Success) {
//                it.data?.vacancies.toVacanciesModels()
//            } else {
//                it
//            }
//        }


//    private fun getData() {
//        val isInternetConnected = Utils.hasInternetConnection(context)
//        if (isInternetConnected) {
//            try {
//                val response = networkSource.fetchData()
//                if (response.isSuccessful && response.body() != null) {
//                    response.body()?.let {
//                        val offers = it.offers?.toOffersModels()
//                        _offers.value = DataResult.Success(offers)
//                        val vacancies = it.vacancies.toVacanciesModels()
//                        _vacanciesChannel.send(DataResult.Success(vacancies))
//                    }
//                } else {
//                    _vacanciesChannel.send(DataResult.Error(response.message()))
//                }
//            } catch (e: Exception) {
//                _vacanciesChannel.send(DataResult.Error(e.toString()))
//            }
//        } else {
//            _vacanciesChannel.send(DataResult.Error(API_INTERNET_MESSAGE))
//        }
//    }


//    private fun getData(): Flow<DataResult<DataContainer>> = flow {
//        val isInternetConnected = Utils.hasInternetConnection(context)
//        if (isInternetConnected) {
//            try {
//                val response = networkSource.fetchData()
//                if (response.isSuccessful && response.body() != null) {
//
//                    emit(DataResult.Success(response.body()))
//                } else {
//                    emit(DataResult.Error(response.message()))
//                }
//            } catch (e: Exception) {
//                emit(DataResult.Error(e.toString()))
//            }
//        } else {
//            emit(DataResult.Error(API_INTERNET_MESSAGE))
//        }
//    }.flowOn(Dispatchers.IO)
}
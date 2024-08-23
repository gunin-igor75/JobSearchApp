package com.github.gunin_igor75.network

import retrofit2.Response

interface NetworkSource<T> {

    suspend fun fetchData(): Response<T>

}
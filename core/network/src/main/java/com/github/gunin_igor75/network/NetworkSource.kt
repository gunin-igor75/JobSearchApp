package com.github.gunin_igor75.network

import retrofit2.Response

interface NetworkSource<T> {

    fun fetchData(): Response<T>

}
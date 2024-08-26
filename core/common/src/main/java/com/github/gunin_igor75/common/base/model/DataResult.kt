package com.github.gunin_igor75.common.base.model

sealed class DataResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Initial<T>: DataResult<T>()
    class Loading<T>:DataResult<T>()
    class Success<T>(data: T?) : DataResult<T>(data = data)
    class Error<T>(message: String?) : DataResult<T>(message = message)
}

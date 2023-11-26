package com.thondigital.nc.common.wrapper

import kotlinx.serialization.Serializable

typealias SimpleResource = DataResult<Unit>

@Serializable
sealed class DataResult<out T> {
    class Success<T>(val data: T) : DataResult<T>()

    data class Error<T>(val exception: Exception) : DataResult<T>()
}

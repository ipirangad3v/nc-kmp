package com.thondigital.nc.common.exception

sealed class CacheException : Exception() {
    object NoResults : NetworkException()
}

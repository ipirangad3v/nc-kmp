package com.thondigital.nc.domain.repository

import com.thondigital.nc.domain.models.HomeResponse

interface HomeRepository {
    suspend fun getHome(): HomeResponse
}

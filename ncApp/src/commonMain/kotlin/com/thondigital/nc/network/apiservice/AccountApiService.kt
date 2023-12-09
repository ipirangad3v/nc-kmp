package com.thondigital.nc.network.apiservice

import com.thondigital.nc.network.model.request.PasswordRequest
import com.thondigital.nc.network.model.response.AccountNetworkModel
import com.thondigital.nc.network.model.response.GenericResponse

interface AccountApiService {
    suspend fun getAccount(): AccountNetworkModel

    suspend fun updatePassword(passwordRequest: PasswordRequest): GenericResponse

    suspend fun deleteAccount(): GenericResponse
}

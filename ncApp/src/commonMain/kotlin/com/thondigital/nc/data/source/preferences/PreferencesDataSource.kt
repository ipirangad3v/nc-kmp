package com.thondigital.nc.data.source.preferences

import com.thondigital.nc.data.model.TokensDataModel

interface PreferencesDataSource {
    fun storeTokens(tokens: TokensDataModel)

    fun getAccessToken(): String

    fun getRefreshToken(): String

    fun deleteTokens()
}

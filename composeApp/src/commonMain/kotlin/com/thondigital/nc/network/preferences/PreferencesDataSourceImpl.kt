package com.thondigital.nc.network.preferences

import com.thondigital.nc.data.model.TokensDataModel
import com.thondigital.nc.data.source.preferences.PreferencesDataSource

class PreferencesDataSourceImpl : PreferencesDataSource {
    override fun storeTokens(tokens: TokensDataModel) {
    }

    override fun getAccessToken(): String {
        return "new token"
    }

    override fun getRefreshToken(): String {
        return "new token"
    }

    override fun deleteTokens() {
    }
}

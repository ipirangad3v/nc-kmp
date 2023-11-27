package com.thondigital.nc.network.preferences

import com.thondigital.nc.AppDatabase
import com.thondigital.nc.data.model.TokensDataModel
import com.thondigital.nc.data.source.preferences.PreferencesDataSource
import com.thondigital.nc.network.sqldriver.DatabaseDriverFactory

class PreferencesDataSourceImpl(
    databaseDriverFactory: DatabaseDriverFactory,
) : PreferencesDataSource {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.preferencesQueries

    override fun storeTokens(tokens: TokensDataModel) {
        dbQuery.transaction {
            dbQuery.insertTokens(
                tokens.accessToken,
                tokens.refreshToken,
            )
        }
    }

    override fun getAccessToken(): String = dbQuery.getAccessToken().executeAsOne().access_token ?: ""

    override fun getRefreshToken(): String = dbQuery.getRefreshToken().executeAsOne().refresh_token ?: ""

    override fun deleteTokens() {
        dbQuery.deleteTokens()
    }
}

package com.thondigital.nc.network.source.cache

import com.thondigital.nc.AppDatabase
import com.thondigital.nc.data.model.TokensDataModel
import com.thondigital.nc.data.source.preferences.PreferencesDataSource
import com.thondigital.nc.network.sqldriver.DatabaseDriverFactory

class PreferencesDataSourceImpl(
    databaseDriverFactory: DatabaseDriverFactory
) : PreferencesDataSource {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.preferencesQueries

    override fun storeTokens(tokens: TokensDataModel) {
        dbQuery.transaction {
            deleteTokens()
            dbQuery.insertTokens(
                tokens.accessToken,
                tokens.refreshToken
            )
        }
    }

    override fun getAccessToken(): String {
        return try {
            dbQuery.getAccessToken().executeAsOne().access_token ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    override fun getRefreshToken(): String {
        return try {
            dbQuery.getRefreshToken().executeAsOne().refresh_token ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    override fun deleteTokens() {
        dbQuery.deleteTokens()
    }
}

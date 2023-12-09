package com.thondigital.nc.network.source.cache

import com.thondigital.nc.data.model.AccountDataModel
import com.thondigital.nc.data.source.cache.account.AccountCacheDataSource
import com.thondigital.nc.network.sqldriver.DatabaseDriverFactory
import com.thondigital.nc.network.utils.toBoolean
import com.thondigital.nc.network.utils.toLong
import digital.thon.nc.AppDatabase
import io.ktor.util.logging.KtorSimpleLogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class AccountCacheDataSourceImpl(
    databaseDriverFactory: DatabaseDriverFactory
) : AccountCacheDataSource {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.accountQueries

    override fun fetchAccount(): Flow<AccountDataModel> =
        flow {
            dbQuery.fetchAccount().executeAsOneOrNull()?.let { account ->
                emit(
                    AccountDataModel(
                        pk = 1,
                        email = account.email ?: "",
                        username = account.username ?: "",
                        isAdmin = account.isAdmin.toBoolean()
                    )
                )
            }
        }.catch {
            KtorSimpleLogger("AccountCacheDataSourceImpl").error("Error fetching account: ${it.message}")
        }

    override suspend fun storeAccount(account: AccountDataModel) =
        dbQuery.transaction {
            try {
                dbQuery.storeAccount(
                    account.username,
                    account.email,
                    account.isAdmin.toLong()
                )
            } catch (e: Exception) {
                KtorSimpleLogger(
                    "AccountCacheDataSourceImpl"
                ).error("Error storing account: ${e.message}")
            }
        }

    override suspend fun deleteAccount() {
        dbQuery.deleteAccount()
    }
}

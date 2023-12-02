package com.thondigital.nc.di

import com.thondigital.nc.data.connectivity.ConnectivityChecker
import com.thondigital.nc.data.source.network.account.AccountNetworkDataSource
import com.thondigital.nc.data.source.network.auth.AuthNetworkDataSource
import com.thondigital.nc.data.source.preferences.PreferencesDataSource
import com.thondigital.nc.network.apiservice.AuthApiService
import com.thondigital.nc.network.apiservice.AuthApiServiceImpl
import com.thondigital.nc.network.connectivity.DefaultConnectivityChecker
import com.thondigital.nc.network.mapper.AccountNetworkDataMapper
import com.thondigital.nc.network.mapper.TokensNetworkDataMapper
import com.thondigital.nc.network.source.AccountNetworkDataSourceImpl
import com.thondigital.nc.network.source.AuthNetworkDataSourceImpl
import com.thondigital.nc.network.source.PreferencesDataSourceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val networkModule =
    module {
        single<ConnectivityChecker> { DefaultConnectivityChecker() }
        single<AuthNetworkDataSource> {
            AuthNetworkDataSourceImpl(
                get(),
                TokensNetworkDataMapper(),
                get()
            )
        }
        single<AccountNetworkDataSource> {
            AccountNetworkDataSourceImpl(
                get(),
                AccountNetworkDataMapper(),
                get()
            )
        }
        single<PreferencesDataSource> {
            PreferencesDataSourceImpl(
                get()
            )
        }
        single<HttpClient> {
            HttpClient {
                install(Logging) {
                    logger = Logger.SIMPLE
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            useAlternativeNames = false
                            explicitNulls = false
                        }
                    )
                }

                defaultRequest {
                    contentType(ContentType.Application.Json)
                }
            }
        }
        single<AuthApiService> { AuthApiServiceImpl(get()) }
    }

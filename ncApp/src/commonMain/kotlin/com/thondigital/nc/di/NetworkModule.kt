package com.thondigital.nc.di

import com.thondigital.nc.data.connectivity.ConnectivityChecker
import com.thondigital.nc.data.source.network.account.AccountNetworkDataSource
import com.thondigital.nc.data.source.network.auth.AuthNetworkDataSource
import com.thondigital.nc.data.source.network.streaming.StreamingDataSource
import com.thondigital.nc.data.source.preferences.PreferencesDataSource
import com.thondigital.nc.network.apiservice.AuthApiService
import com.thondigital.nc.network.apiservice.AuthApiServiceImpl
import com.thondigital.nc.network.apiservice.StreamingApiService
import com.thondigital.nc.network.apiservice.StreamingApiServiceImpl
import com.thondigital.nc.network.connectivity.DefaultConnectivityChecker
import com.thondigital.nc.network.mapper.AccountNetworkDataMapper
import com.thondigital.nc.network.mapper.TokensNetworkDataMapper
import com.thondigital.nc.network.source.AccountNetworkDataSourceImpl
import com.thondigital.nc.network.source.AuthNetworkDataSourceImpl
import com.thondigital.nc.network.source.PreferencesDataSourceImpl
import com.thondigital.nc.network.source.StreamingDataSourceImpl
import org.koin.dsl.module

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

        single<StreamingDataSource> { StreamingDataSourceImpl(get()) }

        single<AuthApiService> { AuthApiServiceImpl() }
        single<StreamingApiService> { StreamingApiServiceImpl() }
    }

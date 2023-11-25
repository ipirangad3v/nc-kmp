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
import com.thondigital.nc.network.preferences.PreferencesDataSourceImpl
import com.thondigital.nc.network.source.DefaultAccountNetworkDataSource
import com.thondigital.nc.network.source.DefaultAuthNetworkDataSource
import org.koin.dsl.module

val networkModule =
    module {
        single<ConnectivityChecker> { DefaultConnectivityChecker() }
        single<AuthNetworkDataSource> {
            DefaultAuthNetworkDataSource(
                get(),
                TokensNetworkDataMapper(),
                get(),
            )
        }
        single<AccountNetworkDataSource> {
            DefaultAccountNetworkDataSource(
                get(),
                AccountNetworkDataMapper(),
                get(),
            )
        }
        single<PreferencesDataSource> { PreferencesDataSourceImpl() }

        single<AuthApiService> { AuthApiServiceImpl() }
    }

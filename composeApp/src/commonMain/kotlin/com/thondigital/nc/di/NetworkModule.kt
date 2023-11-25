package com.thondigital.nc.di

import com.thondigital.nc.data.connectivity.ConnectivityChecker
import com.thondigital.nc.data.source.network.account.AccountNetworkDataSource
import com.thondigital.nc.data.source.network.auth.AuthNetworkDataSource
import com.thondigital.nc.network.connectivity.DefaultConnectivityChecker
import com.thondigital.nc.network.source.DefaultAccountNetworkDataSource
import com.thondigital.nc.network.source.DefaultAuthNetworkDataSource
import org.koin.dsl.module

val networkModule =
    module {
        single<ConnectivityChecker> { DefaultConnectivityChecker() }

        single<AuthNetworkDataSource> { DefaultAuthNetworkDataSource(get(), get(), get()) }
        single<AccountNetworkDataSource> { DefaultAccountNetworkDataSource(get(), get(), get()) }
    }

package com.thondigital.nc.di

import org.koin.core.context.startKoin

// need this to start koin from ios, do not remove
@Suppress("unused")
fun initKoin() {
    // start Koin
    startKoin {
        modules(presentationModule, dataModule, networkModule,dispatcherModule)
    }.koin
}

package com.thondigital.nc.di

import org.koin.core.context.startKoin

@Suppress("unused")
//need this to start koin from ios, do not remove
fun initKoin() {
    // start Koin
    startKoin {
        modules(appModule)
    }.koin
}
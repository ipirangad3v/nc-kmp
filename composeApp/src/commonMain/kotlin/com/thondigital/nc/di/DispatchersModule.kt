package com.thondigital.nc.di

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {

    single(named("defaultDispatcher")) {
        Dispatchers.Default
    }

    single(named("ioDispatcher")) {
        Dispatchers.IO
    }

    single(named("mainDispatcher")) {
        Dispatchers.Main
    }
}
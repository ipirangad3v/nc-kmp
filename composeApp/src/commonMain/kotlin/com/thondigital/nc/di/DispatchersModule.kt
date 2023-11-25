package com.thondigital.nc.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {

    single<CoroutineDispatcher>(named("defaultDispatcher")) {
        Dispatchers.Default
    }

    single<CoroutineDispatcher>(named("ioDispatcher")) {
        Dispatchers.IO
    }

    single<CoroutineDispatcher>(named("mainDispatcher")) {
        Dispatchers.Main
    }
}
package com.thondigital.nc.di

import com.thondigital.nc.network.sqldriver.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module =
    module {
        single { DatabaseDriverFactory(get()) }
    }

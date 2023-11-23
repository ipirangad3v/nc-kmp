package com.thondigital.nc

import android.app.Application
import com.thondigital.nc.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NCApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NCApp)
            modules(appModule)
        }
    }
}

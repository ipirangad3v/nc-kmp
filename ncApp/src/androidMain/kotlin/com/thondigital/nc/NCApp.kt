package com.thondigital.nc

import Kmule.startKmule
import android.app.Application
import android.content.Context
import com.thondigital.nc.di.dataModule
import com.thondigital.nc.di.dispatcherModule
import com.thondigital.nc.di.networkModule
import com.thondigital.nc.di.platformModule
import com.thondigital.nc.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NCApp : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NCApp)
            modules(
                platformModule(),
                presentationModule,
                dataModule,
                dispatcherModule,
                networkModule
            )
        }
        appContext = applicationContext
        startKmule { appContext }
    }
}

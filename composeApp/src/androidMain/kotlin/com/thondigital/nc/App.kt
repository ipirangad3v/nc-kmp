package com.thondigital.nc

import android.app.Application
import com.thondigital.nc.di.dataModule
import com.thondigital.nc.di.dispatcherModule
import com.thondigital.nc.di.networkModule
import com.thondigital.nc.di.platformModule
import com.thondigital.nc.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NCApp : Application() {
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
                networkModule,
            )
        }
    }
}

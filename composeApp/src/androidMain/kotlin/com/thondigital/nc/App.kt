package com.thondigital.nc

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.thondigital.nc.di.dataModule
import com.thondigital.nc.di.dispatcherModule
import com.thondigital.nc.di.networkModule
import com.thondigital.nc.di.platformModule
import com.thondigital.nc.di.presentationModule
import com.thondigital.nc.di.streamingModule
import com.thondigital.nc.utils.NotificationWorker
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
                streamingModule(),
                presentationModule,
                dataModule,
                dispatcherModule,
                networkModule,
            )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel =
            NotificationChannel(NotificationWorker.channelId, NotificationWorker.name, importance)
        channel.description = NotificationWorker.description

        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

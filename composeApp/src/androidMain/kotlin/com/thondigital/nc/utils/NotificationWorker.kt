package com.thondigital.nc.utils

import android.app.Notification
import android.content.Context
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.thondigital.nc.R

class NotificationWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        val showTitle = inputData.getString(titleKey)
        val showID = inputData.getInt(showIDKey, 1)

        val builder =
            NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Reminder ⏰")
                .setContentText("$showTitle Is Now Live On WPRK 91.5FM")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND))

        with(NotificationManagerCompat.from(context)) {
            notify(showID, builder.build())
        }

        return Result.success()
    }

    companion object {
        const val name = "Schedule Shows"
        const val description = "Used to Schedule Reminders for Shows"
        const val notificationId = "notificationId"
        const val channelId = "channel1"
        const val titleKey = "titleKey"
        const val showIDKey = "messageKey"
    }
}

/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 25.01.19 16:43
 */

package com.javagic.smartalarmclock.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.AlarmApp
import com.javagic.smartalarmclock.base.AlarmApp.Companion.instance
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.trigger.TriggerActivity

val notificationManager get() = AlarmApp.instance.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
private const val NEW_TASKS_CHANNEL_ID = "NewTaskNotificationChannel"
const val NEW_TASKS_NOTIFICATION_ID = 312

private val notificationBuilder by lazy {
  NotificationCompat.Builder(AlarmApp.instance, NEW_TASKS_CHANNEL_ID)
      .setSmallIcon(R.drawable.ic_time)
      .setContentText("notificationContent")
      .setAutoCancel(true)
      .setOngoing(true)
      .setDefaults(Notification.DEFAULT_ALL)
      .setContentTitle("notificationTitle")
}

fun cancelNotification() {
  notificationManager.cancel(NEW_TASKS_NOTIFICATION_ID)
}

fun createNotification(alarmItem: AlarmItem): Notification {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    if (notificationManager.notificationChannels.none { it.id == NEW_TASKS_CHANNEL_ID }) {
      val name = "channelName"
      val importance = NotificationManager.IMPORTANCE_DEFAULT
      val channel = NotificationChannel(NEW_TASKS_CHANNEL_ID, name, importance)
      notificationManager.createNotificationChannel(channel)
    }
  }
  return notificationBuilder
      .setContentIntent(PendingIntent.getActivity(instance, 0,
          TriggerActivity.intent(instance, alarmItem) , 0))
      .build()
}
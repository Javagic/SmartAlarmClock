/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.01.19 19:25
 */

package com.javagic.smartalarmclock.utils.ext

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Bundle
import android.os.Vibrator
import android.support.annotation.StringRes
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.AlarmApp.Companion.instance
import com.javagic.smartalarmclock.data.ALARM_EXTRA
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.manager.AlarmService
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

fun string(@StringRes stringId: Int) = instance.getString(stringId) ?: ""
fun tag() = "SmartAlarmClock"
fun alarmManager() = instance.getSystemService(Context.ALARM_SERVICE) as AlarmManager
fun vibrator() = instance.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

val dateTimeFormatLong: SimpleDateFormat
  get() = SimpleDateFormat(string(R.string.date_time_format_long), Locale(string(R.string.lang)))

fun scheduleAlarm(alarm: AlarmItem) {
  val intent = Intent(instance, AlarmService::class.java).apply {
    putExtras(alarm.asBundle())
  }
  val pendingIntent = PendingIntent.getService(instance, alarm.id.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
  val alarmClockInfo = AlarmManager.AlarmClockInfo(
      alarm.nextTrigger.timeInMillis,
      pendingIntent)
  alarmManager().setAlarmClock(alarmClockInfo, pendingIntent)
  Timber.tag(tag()).i("Alarm is scheduled to %s", dateTimeFormatLong.format(Date(alarm.nextTrigger.timeInMillis)))
}

fun cancelAlarm(alarm: AlarmItem) {
  val intent = Intent(instance, AlarmService::class.java)
  val pendingIntent = PendingIntent.getService(instance, alarm.id.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
  alarmManager().cancel(pendingIntent)
}

val AlarmItem.nextTrigger: Calendar
  get() = Calendar.getInstance().apply {
    set(Calendar.HOUR_OF_DAY, timeHour)
    set(Calendar.MINUTE, timeMinute)
    set(Calendar.SECOND, second)
    if (timeInMillis - System.currentTimeMillis() < 0) {
      add(Calendar.DATE, 1)
    }
  }

val defaultUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
    ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
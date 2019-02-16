package com.javagic.smartalarmclock.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import com.javagic.smartalarmclock.base.AlarmApp
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.manager.AlarmService
import com.javagic.smartalarmclock.utils.ext.alarmManager
import com.javagic.smartalarmclock.utils.ext.dateTimeFormatLong
import com.javagic.smartalarmclock.utils.ext.defaultUri
import com.javagic.smartalarmclock.utils.ext.tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import java.util.Calendar.*

object AlarmRepository {

  suspend fun createAlarm() = withContext(IO) {
    val newAlarm = defaultAlarm()
    newAlarm.id = AlarmApp.database.alarmDao().insert(defaultAlarm())//TODO
    newAlarm
  }

  fun updateAlarm(alarm: AlarmItem) = GlobalScope.launch(IO) {
    AlarmApp.database.alarmDao().update(alarm)
  }

  private fun defaultAlarm() = AlarmItem("Alarm",
      "",
      Calendar.getInstance().get(HOUR_OF_DAY),
      Calendar.getInstance().get(MINUTE),
      Calendar.getInstance().get(SECOND),
      true,
      defaultUri.path//TODO
  )

  fun scheduleAlarm(alarm: AlarmItem) = GlobalScope.launch(Dispatchers.IO) {
    AlarmApp.database.alarmDao().enable(alarm.id)
    val intent = Intent(AlarmApp.instance, AlarmService::class.java).apply {
      putExtras(alarm.asBundle())
    }
    val pendingIntent = PendingIntent.getService(AlarmApp.instance, alarm.id.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
    val alarmClockInfo = AlarmManager.AlarmClockInfo(
        alarm.nextTrigger.timeInMillis,
        pendingIntent)
    alarmManager().setAlarmClock(alarmClockInfo, pendingIntent)
    Timber.tag(tag()).i("Alarm is scheduled to %s", dateTimeFormatLong.format(Date(alarm.nextTrigger.timeInMillis)))
  }

  fun cancelAlarm(alarmId: Long) = GlobalScope.launch(Dispatchers.IO) {
    AlarmApp.database.alarmDao().disable(alarmId)
    val intent = Intent(AlarmApp.instance, AlarmService::class.java)
    val pendingIntent = PendingIntent.getService(AlarmApp.instance, alarmId.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
    alarmManager().cancel(pendingIntent)
    Timber.tag(tag()).i("Alarm is canceled")

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
}
/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.01.19 19:25
 */

package com.javagic.smartalarmclock.utils.ext

import android.app.AlarmManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Vibrator
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.AlarmApp.Companion.instance
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.text.SimpleDateFormat
import java.util.*

fun string(@StringRes stringId: Int) = instance.getString(stringId) ?: ""
fun tag() = "SmartAlarmClock"
fun alarmManager() = instance.getSystemService(Context.ALARM_SERVICE) as AlarmManager
fun vibrator() = instance.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
fun sensor() = instance.getSystemService(AppCompatActivity.SENSOR_SERVICE) as android.hardware.SensorManager
fun toast(message: String) = Toast.makeText(instance, message, Toast.LENGTH_LONG).show()
fun ui(block: suspend CoroutineScope.() -> Unit) = GlobalScope.launch(Main, block = block)
fun <T> asyncDb(block: suspend CoroutineScope.() -> T): Deferred<T> = GlobalScope.async(IO, block = block)

val dateTimeFormatLong: SimpleDateFormat
  get() = SimpleDateFormat(string(R.string.date_time_format_long), Locale(string(R.string.lang)))


val defaultUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
    ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
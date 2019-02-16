/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.01.19 21:31
 */

package com.javagic.smartalarmclock.manager

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import com.javagic.smartalarmclock.base.AlarmApp.Companion.database
import com.javagic.smartalarmclock.base.AlarmApp.Companion.instance
import com.javagic.smartalarmclock.repository.AlarmRepository.scheduleAlarm
import com.javagic.smartalarmclock.utils.ext.tag
import com.javagic.smartalarmclock.utils.ext.toast
import timber.log.Timber

class BootReceiver : BroadcastReceiver() {

  companion object {
    fun enable() {
      val receiver = ComponentName(instance, BootReceiver::class.java)
      instance.packageManager.setComponentEnabledSetting(
          receiver,
          PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
          PackageManager.DONT_KILL_APP
      )
    }

    fun disable() {
      val receiver = ComponentName(instance, BootReceiver::class.java)
      instance.packageManager.setComponentEnabledSetting(
          receiver,
          PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
          PackageManager.DONT_KILL_APP
      )
    }
  }

  override fun onReceive(context: Context, intent: Intent) {//TODO
    toast(intent.action)
    database.alarmDao().all().forEach {
      if (it.enabled)
        scheduleAlarm(it)
    }
    val bootCompleted = (Build.VERSION.SDK_INT >= 24 && Intent.ACTION_LOCKED_BOOT_COMPLETED == intent.action)
        || Intent.ACTION_BOOT_COMPLETED == intent.action
    if (!bootCompleted) return
    Timber.tag(tag()).i("BootReceiver received %s", intent.action)

  }

}
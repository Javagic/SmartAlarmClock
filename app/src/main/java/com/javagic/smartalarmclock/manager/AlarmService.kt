/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.01.19 21:27
 */

package com.javagic.smartalarmclock.manager

import android.app.IntentService
import android.content.Intent
import com.javagic.smartalarmclock.base.AlarmApp.Companion.instance
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.music.SoundService
import com.javagic.smartalarmclock.trigger.TriggerActivity
import com.javagic.smartalarmclock.utils.ext.tag
import timber.log.Timber

class AlarmService : IntentService(AlarmService::class.simpleName) {

  override fun onHandleIntent(intent: Intent?) {
    Timber.tag(tag()).i("AlarmService is triggered")
    intent ?: return
    val alarm = AlarmItem(intent.extras)
    startService(Intent(instance, SoundService::class.java).putExtras(alarm.asBundle()))
  }

}
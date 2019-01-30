/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 25.01.19 22:10
 */

package com.javagic.smartalarmclock.music

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.os.PowerManager
import android.os.VibrationEffect
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.AlarmApp
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.trigger.TriggerActivity
import com.javagic.smartalarmclock.utils.NEW_TASKS_NOTIFICATION_ID
import com.javagic.smartalarmclock.utils.createNotification
import com.javagic.smartalarmclock.utils.ext.vibrator
import java.io.File


class SoundService : Service() {
  private val mediaPlayer by lazy { MediaPlayer.create(this, R.raw.bug) }
  private val vibrationPattern: LongArray = arrayOf<Long>(0, 800, 300).toLongArray()
  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    intent?.apply {
      val alarmItem = AlarmItem(extras)
      if (File(alarmItem.musicUri).exists()) {
        mediaPlayer.setDataSource(alarmItem.musicUri)
      }
      mediaPlayer.apply {
        setWakeMode(this@SoundService, PowerManager.PARTIAL_WAKE_LOCK)
        isLooping = true
        start()
      }
      vibrator().apply {
        if (hasVibrator()) {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrate(VibrationEffect.createWaveform(vibrationPattern, 1))
          } else {
            vibrate(vibrationPattern, 1)
          }
        }
      }
      startForeground(NEW_TASKS_NOTIFICATION_ID, createNotification(alarmItem))
      TriggerActivity.start(AlarmApp.instance, alarmItem)
    }
    return START_NOT_STICKY
  }

  override fun onBind(intent: Intent?): IBinder? = null

  override fun onDestroy() {
    super.onDestroy()
    mediaPlayer.apply {
      stop()
      release()
    }
    vibrator().cancel()
  }
}
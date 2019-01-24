/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 25.01.19 22:10
 */

package com.javagic.smartalarmclock.music

import android.app.IntentService
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.IBinder
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.data.ALARM_EXTRA
import com.javagic.smartalarmclock.data.AlarmItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SoundService : IntentService(SoundService::class.java.name) {
  private lateinit var mediaPlayer: MediaPlayer
  lateinit var audioManager: AudioManager
  var volume: Int = 0

  override fun onHandleIntent(intent: Intent?) {
    intent ?: return
    intent.getParcelableExtra<AlarmItem>(ALARM_EXTRA)
      mediaPlayer = MediaPlayer.create(this@SoundService, R.raw.sound)
      mediaPlayer.isLooping = true
      mediaPlayer.start()
  }

  override fun onBind(intent: Intent?): IBinder? = null

  override fun onDestroy() {
    super.onDestroy()
    mediaPlayer.stop()
    mediaPlayer.release()
  }
}
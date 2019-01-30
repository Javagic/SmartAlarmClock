/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 30.01.19 19:48
 */

package com.javagic.smartalarmclock.music

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.media.RingtoneManager
import android.provider.MediaStore
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.AlarmApp.Companion.instance
import com.javagic.smartalarmclock.utils.ext.string
import java.util.concurrent.TimeUnit

class SelectMusicViewModel : ViewModel() {

  private val _music = MutableLiveData<List<MusicItem>>()
  val music: LiveData<List<MusicItem>> = _music
  val selectedItem = MutableLiveData<MusicItem>()

  init {
    loadRingtones()
  }

  private fun loadMusic() {
    val result = ArrayList<MusicItem>()
    val projection = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.AudioColumns.TITLE,
        MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.AudioColumns.DATA,
        MediaStore.Audio.AudioColumns.ALBUM,
        MediaStore.Audio.ArtistColumns.ARTIST
    )
    val musicCursor = instance.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null, null, null)
    musicCursor?.apply {
      if (moveToFirst()) {
        do {
          val id = getLong(0)
          val title = getString(1)
          val duration = getString(2)
          val uri = getString(3)
          val album = getString(4)
          val artist = getString(5)
          result.add(MusicItem(title, duration.format(), uri))
        } while (moveToNext())
      }
    }
    musicCursor?.close()
//    _music.postValue(result)
  }

  private fun String.format() = String.format(string(R.string.music_time_format),
      TimeUnit.MILLISECONDS.toMinutes(toLong()),
      TimeUnit.MILLISECONDS.toSeconds(toLong()) -
          TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(toLong()))
  )

  fun loadRingtones() {
    val result = ArrayList<MusicItem>()
    RingtoneManager(instance).apply {
      setType(RingtoneManager.TYPE_ALARM)
    }
        .cursor.apply {
      while (moveToNext()) {
        val notificationTitle = getString(RingtoneManager.TITLE_COLUMN_INDEX)
        val notificationUri = getString(RingtoneManager.URI_COLUMN_INDEX) + "/" + getString(RingtoneManager.ID_COLUMN_INDEX)
        result.add(MusicItem(notificationTitle, "", notificationUri))
      }
      close()
    }
    _music.postValue(result)
  }
}
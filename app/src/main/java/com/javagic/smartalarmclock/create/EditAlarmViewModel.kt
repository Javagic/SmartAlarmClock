/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.01.19 17:25
 */

package com.javagic.smartalarmclock.create

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.music.MusicItem

class EditAlarmViewModel : ViewModel() {

  lateinit var alarm: AlarmItem
  val musicItem = MutableLiveData<MusicItem>()
}
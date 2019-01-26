/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.01.19 15:54
 */

package com.javagic.smartalarmclock.main

import android.arch.lifecycle.ViewModel
import com.javagic.smartalarmclock.base.AlarmApp
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.utils.ext.scheduleAlarm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
  private val job = Job()

  val alarms = AlarmApp.database.alarmItem().allMinimal()


  fun createAlarm(item: AlarmItem) {//remove
    GlobalScope.launch(Dispatchers.IO) {
      item.id = AlarmApp.database.alarmItem().insert(item)
      scheduleAlarm(item)
    }
  }
  fun deleteAlarm() {

  }

  override fun onCleared() {
    job.cancel()
    super.onCleared()
  }
}
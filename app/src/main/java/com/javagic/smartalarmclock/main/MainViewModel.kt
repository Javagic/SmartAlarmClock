/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.01.19 15:54
 */

package com.javagic.smartalarmclock.main

import android.arch.lifecycle.ViewModel
import com.javagic.smartalarmclock.base.AlarmApp
import kotlinx.coroutines.Job

class MainViewModel : ViewModel() {
  private val job = Job()

  val alarms = AlarmApp.database.alarmDao().allData()

  override fun onCleared() {
    job.cancel()
    super.onCleared()
  }
}
/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.01.19 17:25
 */

package com.javagic.smartalarmclock.create

import android.arch.lifecycle.ViewModel
import com.javagic.smartalarmclock.base.AlarmApp
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.utils.ext.scheduleAlarm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddAlarmViewModel : ViewModel() {

  fun createAlarm(item: AlarmItem) {
    GlobalScope.launch(Dispatchers.IO) {
      item.id = AlarmApp.database.alarmItem().insert(item)
      scheduleAlarm(item)
    }
  }
}
/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 25.01.19 16:28
 */

package com.javagic.smartalarmclock.trigger

import android.arch.lifecycle.ViewModel
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.data.EMPTY_ALARM_ITEM

class TriggerViewModel : ViewModel() {
  var alarmItem: AlarmItem = EMPTY_ALARM_ITEM
}
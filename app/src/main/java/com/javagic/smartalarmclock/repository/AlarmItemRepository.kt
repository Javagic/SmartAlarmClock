/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 15.06.18 18:43
 */

package com.javagic.smartalarmclock.repository

import android.arch.lifecycle.LiveData
import com.javagic.smartalarmclock.data.AlarmItem


interface AlarmItemRepository {
  fun all(id: Int): LiveData<List<AlarmItem>>
}
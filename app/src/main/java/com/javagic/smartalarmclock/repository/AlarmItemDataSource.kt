/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 15.06.18 18:40
 */

package com.javagic.smartalarmclock.repository

import android.arch.lifecycle.LiveData
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.data.AlarmItemDao
import javax.inject.Inject

class AlarmItemDataSource @Inject constructor(private var alarmItemDao: AlarmItemDao) : AlarmItemRepository {
  override fun all(id: Int): LiveData<List<AlarmItem>> = alarmItemDao.allLive()


}
/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 15.06.18 18:41
 */

package com.javagic.smartalarmclock.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.javagic.smartalarmclock.base.BaseDao

@Dao
abstract class AlarmItemDao : BaseDao<AlarmItem> {
  @Query("SELECT * FROM $ALARM_TABLE")
  abstract fun getData(): List<AlarmItem>

  @Query("SELECT id, name, timeHour, timeMinute   FROM $ALARM_TABLE")
  abstract fun allMinimal(): LiveData<List<AlarmItemMinimal>>
}
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

  @Query("SELECT *   FROM $ALARM_TABLE")
  abstract fun allData(): LiveData<List<AlarmItem>>

  @Query("SELECT *   FROM $ALARM_TABLE")
  abstract fun all(): List<AlarmItem>

  @Query("SELECT * FROM $ALARM_TABLE WHERE id =:alarmId")
  abstract fun get(alarmId: Long): AlarmItem

  @Query("UPDATE $ALARM_TABLE SET enabled = 1 WHERE id =:alarmId")
  abstract fun enable(alarmId: Long)

  @Query("UPDATE $ALARM_TABLE SET enabled = 0 WHERE id =:alarmId")
  abstract fun disable(alarmId: Long)
}
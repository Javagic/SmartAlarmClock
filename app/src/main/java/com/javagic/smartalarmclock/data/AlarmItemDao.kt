/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 15.06.18 18:41
 */

package com.javagic.smartalarmclock.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
abstract class AlarmItemDao {
  @Query("SELECT * FROM $ALARM_TABLE")
  abstract fun getData(): List<AlarmItem>
}
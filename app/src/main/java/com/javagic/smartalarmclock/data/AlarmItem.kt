package com.javagic.smartalarmclock.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

const val ALARM_TABLE = "alarms"

@Entity(tableName = ALARM_TABLE)
data class AlarmItem(val name: String? = null,
                     val song: String,
                     val timeHour: Int,
                     val timeMinute: Int,
                     val enabled: Boolean,
                     val tone: Boolean
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0


}
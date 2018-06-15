package com.javagic.smartalarmclock.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.data.AlarmItemDao
import com.javagic.smartalarmclock.repository.AlarmDatabase.Companion.VERSION

@Database(entities = [AlarmItem::class], version = VERSION, exportSchema = false)
abstract class AlarmDatabase : RoomDatabase() {

  abstract fun alarmItem(): AlarmItemDao

  companion object {
    const val VERSION = 1
  }
}

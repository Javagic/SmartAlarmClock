package com.javagic.smartalarmclock.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.javagic.smartalarmclock.base.AlarmApp.Companion.instance
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.data.AlarmItemDao

private const val VERSION = 1
private const val DB_NAME = "DriverDatabase"

@Database(entities = [AlarmItem::class], version = VERSION, exportSchema = false)
abstract class AlarmDatabase : RoomDatabase() {


  abstract fun alarmItem(): AlarmItemDao

  companion object {

    fun create(): AlarmDatabase =
        Room.databaseBuilder(instance, AlarmDatabase::class.java, DB_NAME)
            .build()
  }
}

package com.javagic.smartalarmclock.base

import android.app.Application
import android.arch.persistence.room.Room
import com.javagic.smartalarmclock.repository.AlarmDatabase

const val ALARM_DATABASE = "alarm_database"

class AlarmApp : Application() {
  companion object {
    lateinit var instance: AlarmApp
      private set
  }


  override fun onCreate() {
    super.onCreate()
    instance = this
    Room.databaseBuilder(this, AlarmDatabase::class.java, ALARM_DATABASE).build()
  }

}

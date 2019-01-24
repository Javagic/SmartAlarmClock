package com.javagic.smartalarmclock.base

import android.app.Application
import com.javagic.smartalarmclock.repository.AlarmDatabase
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

const val ALARM_DATABASE = "alarm_database"

class AlarmApp : Application() {

  companion object {
    lateinit var instance: AlarmApp
      private set

    lateinit var database: AlarmDatabase
      private set
  }


  override fun onCreate() {
    super.onCreate()
    instance = this
    database = AlarmDatabase.create()
    initLeakCanary()
    Timber.plant(Timber.DebugTree())
  }

  private fun initLeakCanary() {
    if (!LeakCanary.isInAnalyzerProcess(this)) {
      LeakCanary.install(this)
    }
  }
}

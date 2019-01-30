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

//    if (Build.VERSION.SDK_INT >= 25) {
//      val shortcutInfo = ShortcutInfo.Builder(this, SHOTCUT_ID_INVOKE_SHARE)
//          .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher_round))
//          .setShortLabel(getString(R.string.shortcut_invoke_share))
//          .setIntent(SharingActivity.getIntent(this.applicationContext).apply {
//            action = Intent.ACTION_DEFAULT
//          })
//          .build()
//      getSystemService(ShortcutManager::class.java).dynamicShortcuts = listOf(shortcutInfo)
//    }
  }

  private fun initLeakCanary() {
    if (!LeakCanary.isInAnalyzerProcess(this)) {
      LeakCanary.install(this)
    }
  }
}

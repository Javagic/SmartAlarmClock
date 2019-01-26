/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 25.01.19 16:27
 */

package com.javagic.smartalarmclock.trigger

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.BaseActivity
import com.javagic.smartalarmclock.data.ALARM_EXTRA
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.music.SoundService
import com.javagic.smartalarmclock.utils.cancelNotification
import com.javagic.smartalarmclock.utils.ext.viewModel
import kotlinx.android.synthetic.main.activity_trigger.*


class TriggerActivity : BaseActivity<TriggerViewModel>() {

  companion object {
    fun start(from: Context, alarmItem: AlarmItem) = from.startActivity(
        intent(from, alarmItem)
    )

    fun intent(from: Context, alarmItem: AlarmItem) = Intent(from, TriggerActivity::class.java).apply {
      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      putExtra(ALARM_EXTRA, alarmItem)
    }
  }

  override fun provideViewModel(): TriggerViewModel = viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN or
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
        WindowManager.LayoutParams.FLAG_FULLSCREEN or
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
    setContentView(R.layout.activity_trigger)
    viewModel.alarmItem = intent.getParcelableExtra(ALARM_EXTRA)
    viewModel.alarmItem.apply {
      tvTime.text = getString(R.string.trigger_time, timeHour, timeMinute)
    }
    btnTurnOff.setOnClickListener {
      finish()
      stopService(Intent(this, SoundService::class.java))
      cancelNotification()
    }
  }
}
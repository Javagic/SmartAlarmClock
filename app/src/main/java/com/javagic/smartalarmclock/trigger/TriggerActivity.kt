package com.javagic.smartalarmclock.trigger

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.os.Bundle
import android.view.WindowManager
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.BaseActivity
import com.javagic.smartalarmclock.data.ALARM_EXTRA
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.music.SoundService
import com.javagic.smartalarmclock.utils.ext.tag
import com.javagic.smartalarmclock.utils.ext.viewModel
import kotlinx.android.synthetic.main.activity_trigger.*
import timber.log.Timber


class TriggerActivity : BaseActivity<TriggerViewModel>() {

  companion object {
    fun start(from: Context, alarmItem: AlarmItem) = from.startActivity(
        intent(from, alarmItem)
    )

    fun intent(from: Context, alarmItem: AlarmItem) = Intent(from, TriggerActivity::class.java).apply {
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
      alarmWentOff()
    }
    val sensorListener = SensorManager()
    (getSystemService(SENSOR_SERVICE) as android.hardware.SensorManager).apply {
      registerListener(sensorListener, getDefaultSensor(Sensor.TYPE_ACCELEROMETER), android.hardware.SensorManager.SENSOR_DELAY_NORMAL)
    }
    sensorListener.events.observe {
      val x = it.values[0]
      val y = it.values[1]
      val z = it.values[2]
      if (x > 30 || y > 30 || z > 30) {
        Timber.tag(tag()).i("X:${it.values[0]} Y:${it.values[1]}  Z:${it.values[2]} ")
        alarmWentOff()
      }
    }
  }

  private fun alarmWentOff() {
    finish()
    stopService(Intent(this, SoundService::class.java))
//      cancelNotification()
  }
}
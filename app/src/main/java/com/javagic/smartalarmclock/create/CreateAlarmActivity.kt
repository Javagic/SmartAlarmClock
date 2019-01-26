/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.01.19 17:24
 */

package com.javagic.smartalarmclock.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.BaseActivity
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.utils.ext.viewModel
import kotlinx.android.synthetic.main.activity_edit_alarm.*

class CreateAlarmActivity : BaseActivity<AddAlarmViewModel>() {

  companion object {
    private const val EXTRA_ALARM_ID = "CreateAlarmActivity.AlarmId"

    fun start(from: Context, alarmId: Long) = from.startActivity(
            Intent(from, CreateAlarmActivity::class.java).apply {
              putExtra(EXTRA_ALARM_ID, alarmId)
            }
    )
  }

  override fun provideViewModel(): AddAlarmViewModel = viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit_alarm)
    timePicker.setIs24HourView(true)
    btnCancel.setOnClickListener{ finish() }
    btnCreateAlarm.setOnClickListener {
      viewModel.createAlarm(AlarmItem("", "", timePicker.currentHour, timePicker.currentMinute,0, true, true))//TODO change to actual
      finish()
    }
  }

}
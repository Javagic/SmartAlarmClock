/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.01.19 17:24
 */

package com.javagic.smartalarmclock.create

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.content.PermissionChecker
import android.widget.TimePicker
import android.widget.Toast
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.AlarmApp
import com.javagic.smartalarmclock.base.AlarmApp.Companion.database
import com.javagic.smartalarmclock.base.BaseActivity
import com.javagic.smartalarmclock.music.EXTRA_MUSIC_ITEM
import com.javagic.smartalarmclock.music.MusicItem
import com.javagic.smartalarmclock.music.SelectMusicActivity
import com.javagic.smartalarmclock.repository.AlarmRepository
import com.javagic.smartalarmclock.utils.ext.*
import kotlinx.android.synthetic.main.activity_edit_alarm.*
import pub.devrel.easypermissions.EasyPermissions

const val MUSIC_PERMISSIONS_CODE = 100
const val SELECT_RINGTONE_CODE = 101
const val SELECT_MUSIC_CODE = 102

class CreateAlarmActivity : BaseActivity<EditAlarmViewModel>() {

  companion object {
    private const val EXTRA_ALARM_ID = "CreateAlarmActivity.AlarmId"

    fun start(from: Context, alarmId: Long = 0) = from.startActivity(
        Intent(from, CreateAlarmActivity::class.java).apply {
          putExtra(EXTRA_ALARM_ID, alarmId)
        }
    )
  }

  override fun provideViewModel(): EditAlarmViewModel = viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit_alarm)
    timePicker.setIs24HourView(true)
    with(viewModel) {
      ui {
        alarm = if (intent.getLongExtra(EXTRA_ALARM_ID, 0) != 0L)
          asyncDb { database.alarmDao().get(intent.getLongExtra(EXTRA_ALARM_ID, 0)) }.await()
        else AlarmRepository.createAlarm()
        timePicker.hours = alarm.timeHour
        timePicker.minutes = alarm.timeMinute
        musicItem.postValue(MusicItem(
            RingtoneManager.getRingtone(AlarmApp.instance, defaultUri).getTitle(AlarmApp.instance),
            "",
            ""
        ))
      }
      musicItem.observe {
        tvMusicTitle.text = it.name
      }
    }
    btnCancel.setOnClickListener {
      finish()
    }
    btnCreateAlarm.setOnClickListener {
      with(viewModel.alarm) {
        musicUri = viewModel.musicItem.value!!.uri
        song = viewModel.musicItem.value!!.name
        timeHour = timePicker.hours
        timeMinute = timePicker.minutes
        enabled = true
        second = 0
        AlarmRepository.updateAlarm(this)
        AlarmRepository.scheduleAlarm(this)
      }
      finish()
    }
    btnChooseRingtone.setOnClickListener {
      val intent = Intent(RingtoneManager.ACTION_RINGTONE_PICKER)
      startActivityForResult(intent, SELECT_RINGTONE_CODE)
    }
    btnChooseMusic.setOnClickListener {
      withMusicPermission(MUSIC_PERMISSIONS_CODE) {
        startActivityForResult(Intent(this, SelectMusicActivity::class.java), SELECT_MUSIC_CODE)
      }
    }
  }

  //TODO get rid of this permissions
  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == MUSIC_PERMISSIONS_CODE) {
      if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
        startActivity(Intent(this, SelectMusicActivity::class.java))
      } else Toast.makeText(this, string(R.string.permission_music), Toast.LENGTH_LONG).show()
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == Activity.RESULT_OK) {
      when (requestCode) {
        SELECT_RINGTONE_CODE -> viewModel.musicItem.postValue(MusicItem("asd", "", data?.getParcelableExtra<Uri>(RingtoneManager.EXTRA_RINGTONE_PICKED_URI).toString()))
        SELECT_MUSIC_CODE -> data?.getParcelableExtra<MusicItem>(EXTRA_MUSIC_ITEM)?.let { viewModel.musicItem.postValue(it) }
      }
    }
  }
}

var TimePicker.minutes: Int
  get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) minute
  else currentMinute
  set(value) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) minute = value
    else currentMinute = value
  }

var TimePicker.hours: Int
  get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) hour
  else currentHour
  set(value) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) hour = value
    else currentHour = value
  }
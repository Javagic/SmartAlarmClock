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
import android.os.Bundle
import android.support.v4.content.PermissionChecker
import android.widget.Toast
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.AlarmApp.Companion.database
import com.javagic.smartalarmclock.base.BaseActivity
import com.javagic.smartalarmclock.data.AlarmItem
import com.javagic.smartalarmclock.music.EXTRA_MUSIC_ITEM
import com.javagic.smartalarmclock.music.MusicItem
import com.javagic.smartalarmclock.music.SelectMusicActivity
import com.javagic.smartalarmclock.utils.ext.string
import com.javagic.smartalarmclock.utils.ext.viewModel
import com.javagic.smartalarmclock.utils.ext.withMusicPermission
import kotlinx.android.synthetic.main.activity_edit_alarm.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.EasyPermissions

const val MUSIC_PERMISSIONS_CODE = 100
const val SELECT_RINGTONE_CODE = 101
const val SELECT_MUSIC_CODE = 102

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
    intent.getLongExtra(EXTRA_ALARM_ID, 0).let {
      if (it != 0L) {
        GlobalScope.launch(Dispatchers.IO) {
          viewModel.alarm = database.alarmDao().get(it)
          timePicker.currentHour = viewModel.alarm.timeHour
          timePicker.currentMinute = viewModel.alarm.timeMinute
        }
      }
    }
    timePicker.setIs24HourView(true)
    btnCancel.setOnClickListener { finish() }
    btnCreateAlarm.setOnClickListener {
      viewModel.createAlarm(AlarmItem("", "", timePicker.currentHour, timePicker.currentMinute, 0, true, ""))//TODO change to actual
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
    viewModel.musicItem.observe {
      tvMusicTitle.text = it.name
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
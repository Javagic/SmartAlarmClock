package com.javagic.smartalarmclock.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.AlarmsAdapter
import com.javagic.smartalarmclock.base.BaseActivity
import com.javagic.smartalarmclock.create.CreateAlarmActivity
import com.javagic.smartalarmclock.repository.AlarmRepository.cancelAlarm
import com.javagic.smartalarmclock.repository.AlarmRepository.scheduleAlarm
import com.javagic.smartalarmclock.utils.ItemDecorator
import com.javagic.smartalarmclock.utils.ext.viewModel
import kotlinx.android.synthetic.main.activity_main.*
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter


class MainActivity : BaseActivity<MainViewModel>() {
  override fun provideViewModel(): MainViewModel = viewModel()
  private val alarmAdapter = AlarmsAdapter(onClick = { CreateAlarmActivity.start(this, it) },
      onChecked = { checked, item -> if (checked) scheduleAlarm(item) else cancelAlarm(item.id) }
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    rvAlarms.apply {
      layoutManager = LinearLayoutManager(this@MainActivity)
      adapter = alarmAdapter
      addItemDecoration(ItemDecorator(itemOffset = 15))
      VerticalOverScrollBounceEffectDecorator(RecyclerViewOverScrollDecorAdapter(this))
      "test1"
      "test1"
      "test1"
      "test1"
    }

    viewModel.alarms.observe { newList ->
      alarmAdapter.submitList(newList.sortedWith(compareBy({ it.timeHour }, { it.timeMinute })).reversed())//TODO improve  from DataBase
    }

    btnCreateAlarm.setOnClickListener {
      rvAlarms.postDelayed({ rvAlarms.smoothScrollToPosition(0) }, 200)
//      viewModel.createAlarm(AlarmItem("", "", Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + 4, false, ""))
      CreateAlarmActivity.start(this)
    }
  }

}

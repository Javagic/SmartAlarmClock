/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 17:10
 */

package com.javagic.smartalarmclock.main

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.jakewharton.rxbinding2.view.clicks
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.AlarmsAdapter
import com.javagic.smartalarmclock.data.AlarmItemMinimal
import com.javagic.smartalarmclock.ext.view
import dagger.android.DaggerFragment

class AlarmListFragment : DaggerFragment(), AlarmListContract.View {

  override fun showAlarms() {

  }

  private val recyclerView by view<RecyclerView>(R.id.recycler_view)
  private val addAlarmBtn by view<Button>(R.id.add_alarm)
  private val alarmAdapter = AlarmsAdapter(arrayOf(AlarmItemMinimal(), AlarmItemMinimal()).toList())

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
    val fragmentView = inflater.inflate(R.layout.fragment_main, container, false)
    return fragmentView
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val activityLayoutManager = LinearLayoutManager(activity)
    recyclerView.apply {
      layoutManager = activityLayoutManager
      addItemDecoration(DividerItemDecoration(context, activityLayoutManager.orientation))
      itemAnimator = DefaultItemAnimator()
      adapter = alarmAdapter
    }
    addAlarmBtn.clicks().subscribe {}
  }

}
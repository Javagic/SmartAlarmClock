/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 18:27
 */

package com.javagic.smartalarmclock.base

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.data.AlarmItemMinimal
import com.javagic.smartalarmclock.ext.view
import java.util.ArrayList

class AlarmsAdapter(
    alarms: List<AlarmItemMinimal> = ArrayList()
) : BaseAdapter<AlarmItemMinimal, BaseViewHolder<AlarmItemMinimal>>(
    alarms,
    { parent, viewType -> AlarmViewHolder(R.layout.item_alarm, parent) }
) {


  class AlarmViewHolder(layout: Int, parent: ViewGroup) : BaseViewHolder<AlarmItemMinimal>(
      LayoutInflater.from(parent.context).inflate(layout, parent, false)) {

    val name by itemView.view<TextView>(R.id.name)

    override val NO_ITEM: AlarmItemMinimal
      get() = AlarmItemMinimal()

    override fun bind(item: AlarmItemMinimal) {
      super.bind(item)
      name.text = "lalalal"
    }
  }

}
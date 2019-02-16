/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 18:27
 */

package com.javagic.smartalarmclock.base

import android.view.View
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.data.AlarmItem
import kotlinx.android.synthetic.main.item_alarm.view.*

//TODO add animations
class AlarmsAdapter(private val onClick: (Long) -> Unit,
                    val onChecked: (Boolean, AlarmItem) -> Unit
) : RecyclerViewAdapter<AlarmItem>(R.layout.item_alarm, holderFactory = { AlarmItemViewHolder(onClick, onChecked, it) })

private class AlarmItemViewHolder(val onClick: (Long) -> Unit,
                                  val onChecked: (Boolean, AlarmItem) -> Unit,
                                  view: View) : RecyclerViewAdapter.ViewHolder<AlarmItem>(view) {
  override fun bind(item: AlarmItem) {
    with(view) {
      tvName.text = item.name
      tvHour.text = String.format("%02d", item.timeHour)
      tvMinute.text = String.format("%02d", item.timeMinute)
      switchAlarm.isChecked = item.enabled
      switchAlarm.setOnCheckedChangeListener { _, isChecked -> onChecked(isChecked, item) }
    }
    itemView.setOnClickListener { onClick(item.id) }
  }
}

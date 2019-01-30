/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 18:27
 */

package com.javagic.smartalarmclock.base

import android.view.View
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.data.AlarmItemMinimal
import kotlinx.android.synthetic.main.item_alarm.view.*

//TODO add animations
class AlarmsAdapter(private val onClick: (Long) -> Unit) : RecyclerViewAdapter<AlarmItemMinimal>(R.layout.item_alarm, holderFactory = { AlarmItemViewHolder(onClick,it) })

private class AlarmItemViewHolder(val onClick: (Long) -> Unit,view: View) : RecyclerViewAdapter.ViewHolder<AlarmItemMinimal>(view) {
  override fun bind(item: AlarmItemMinimal) {
    view.tvName.text = item.name
    view.tvHour.text = String.format("%02d", item.timeHour)
    view.tvMinute.text = String.format("%02d", item.timeMinute)
    itemView.setOnClickListener { onClick(item.id) }
  }
}

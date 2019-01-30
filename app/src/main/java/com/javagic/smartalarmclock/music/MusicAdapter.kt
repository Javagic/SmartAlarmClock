package com.javagic.smartalarmclock.music

import android.arch.lifecycle.LiveData
import android.view.View
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.RecyclerViewAdapter
import kotlinx.android.synthetic.main.item_music.view.*

class MusicAdapter(private val onClick: (MusicItem) -> Unit) : RecyclerViewAdapter<MusicItem>(R.layout.item_music, holderFactory = { MusicViewHolder(it, onClick) }) {

}

class MusicViewHolder(view: View, val onClick: (MusicItem) -> Unit) : RecyclerViewAdapter.ViewHolder<MusicItem>(view) {
  override fun bind(item: MusicItem) {
    view.tvName.text = item.name
    view.tvDuration.text = item.duration
    view.setOnClickListener { onClick(item) }
  }
}
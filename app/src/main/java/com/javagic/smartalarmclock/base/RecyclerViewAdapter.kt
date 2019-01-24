/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 18:08
 */

package com.javagic.smartalarmclock.base

import android.support.annotation.LayoutRes
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open class RecyclerViewAdapter<T>(
    @LayoutRes private val itemRes: Int,
    diffCallback: DiffUtil.ItemCallback<T> = SimpleDiffCallback(),
    private val holderFactory: (View) -> ViewHolder<T>
) : ListAdapter<T, RecyclerViewAdapter.ViewHolder<T>>(diffCallback) {

  constructor(@LayoutRes itemRes: Int, areItemsTheSame: (T, T) -> Boolean, holderFactory: (View) -> ViewHolder<T>) :
      this(itemRes, object : SimpleDiffCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            areItemsTheSame(oldItem, newItem)
      }, holderFactory)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> =
      holderFactory.invoke(
          LayoutInflater.from(parent.context).inflate(itemRes, parent, false)
      )

  override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) =
      holder.bind(getItem(position))


  abstract class ViewHolder<T>(protected val view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)
  }


  open class SimpleDiffCallback<T> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = areContentsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
  }
}
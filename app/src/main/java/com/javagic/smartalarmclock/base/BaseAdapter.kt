/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 18:08
 */

package com.javagic.smartalarmclock.base

import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import com.javagic.smartalarmclock.ext.bind
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

typealias ViewHolderFactory<T> = (parent: ViewGroup, viewType: Int) -> T

abstract class BaseAdapter<I, T : BaseViewHolder<I>>(
    data: List<I> = ArrayList(),
    private val factory: ViewHolderFactory<T>
) : RecyclerView.Adapter<T>() {

  internal val data: MutableList<I> = ArrayList(data)

  val selectObservable = PublishSubject.create<I>()!!
  val selectWithViewObservable = PublishSubject.create<T>()!!

  fun setData(data: List<I>) {
    this.data.clear()
    this.data.addAll(data)
    notifyDataSetChanged()
  }

  fun getData(): List<I> = this.data

  fun addData(items: List<I>) {
    val prevSize = itemCount
    data.addAll(items)
    notifyItemRangeInserted(prevSize, items.size)
  }

  fun reset() {
    data.clear()
    notifyDataSetChanged()
  }

  override fun onViewRecycled(holder: T) {
    super.onViewRecycled(holder)
    holder.unbind()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T = factory(parent, viewType)

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: T, position: Int) {
    holder.bind(data[position])
    holder.clicks.subscribe {
      selectObservable.onNext(it)
      selectWithViewObservable.onNext(holder)
    }.bind(holder.clicksDisposable)
  }
}

abstract class BaseViewHolder<I>(itemView: View) : RecyclerView.ViewHolder(itemView) {

  protected abstract val NO_ITEM: I

  internal val clicksDisposable = CompositeDisposable()

  private var item: I? = null

  open val clicks: Observable<I>

  init {
    clicks = this.itemView.clicks()
        .map { item ?: NO_ITEM }
        .filter { NO_ITEM != it }
  }

  open fun bind(@NonNull item: I) {
    this.item = item
  }

  fun unbind() {
    clicksDisposable.clear()
  }

  fun getItem() = item
}
/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.01.19 16:40
 */

package com.javagic.smartalarmclock.utils

import android.graphics.Canvas
import android.graphics.Rect
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.javagic.smartalarmclock.base.AlarmApp.Companion.instance

class ItemDecorator(@DrawableRes val dividerId: Int = 0, val itemOffset: Int = 0) : RecyclerView.ItemDecoration() {

  private val divider by lazy { dividerId.takeIf { it != 0 }?.let { ContextCompat.getDrawable(instance, dividerId) } }

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)
    outRect.top = itemOffset + (divider?.intrinsicHeight ?: 0)
  }

  override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
    if (parent != null && divider != null) {
      val dividerLeft = parent.paddingLeft
      val dividerRight = parent.width - parent.paddingRight

      for (i in 0 until parent.childCount) {
        val child = parent.getChildAt(i)
        val params = child.layoutParams as RecyclerView.LayoutParams
        val dividerTop = child.bottom + params.bottomMargin
        val dividerBottom = dividerTop + (divider?.intrinsicHeight ?: 0)
        divider?.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
        divider?.draw(c)
      }
    } else super.onDraw(c, parent, state)
  }
}
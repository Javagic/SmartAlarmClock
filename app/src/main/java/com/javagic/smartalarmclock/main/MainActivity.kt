package com.javagic.smartalarmclock.main

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.BaseActivity
import com.javagic.smartalarmclock.ext.view


class MainActivity : BaseActivity() {
  val container by view<ConstraintLayout>(R.id.container)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    container.setPadding(0, getStatusBarHeight(), 0, 0)
  }

  private fun getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
      result = resources.getDimensionPixelSize(resourceId)
    }
    return result
  }

}

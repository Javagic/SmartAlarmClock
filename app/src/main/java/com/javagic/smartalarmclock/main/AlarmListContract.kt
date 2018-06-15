/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 16.10.18 18:07
 */

package com.javagic.smartalarmclock.main

import android.app.Activity
import com.javagic.smartalarmclock.base.BasePresenter


interface AlarmListContract {
  interface View {
    fun showAlarms()
  }

  interface Presenter : BasePresenter<View> {
    fun setActivity(activity: Activity)
  }
}
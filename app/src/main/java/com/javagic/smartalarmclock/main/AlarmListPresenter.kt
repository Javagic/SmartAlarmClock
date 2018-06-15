/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 16.10.18 18:06
 */

package com.javagic.smartalarmclock.main

import android.app.Activity
import com.javagic.smartalarmclock.repository.AlarmItemRepository
import javax.inject.Inject

class AlarmListPresenter @Inject constructor(alarmItemRepository: AlarmItemRepository) : AlarmListContract.Presenter {

  override fun takeView(view: AlarmListContract.View) {

  }

  override fun dropView() {

  }

  override fun setActivity(activity: Activity) {

  }

}
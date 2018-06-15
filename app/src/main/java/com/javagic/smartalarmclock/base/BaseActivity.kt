/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 17:55
 */

package com.javagic.smartalarmclock.base

import android.annotation.SuppressLint
import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.DaggerActivity

abstract class BaseActivity : AppCompatActivity(){

  @SuppressLint("ResourceType")
  protected fun addFragment(containerViewId: Int, fragment: Fragment) {
    val fragmentTransaction = this.fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
    fragmentTransaction.add(containerViewId, fragment)
    fragmentTransaction.commit()

  }

}
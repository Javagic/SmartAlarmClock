/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 17:55
 */

package com.javagic.smartalarmclock.base

import android.annotation.SuppressLint
import android.app.Fragment
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<out T : ViewModel> : AppCompatActivity() {

  protected val viewModel: T by lazy {
    provideViewModel()
  }

  protected abstract fun provideViewModel(): T

  @SuppressLint("ResourceType")
  protected fun addFragment(containerViewId: Int, fragment: Fragment) {
    val fragmentTransaction = this.fragmentManager.beginTransaction()
    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
    fragmentTransaction.add(containerViewId, fragment)
    fragmentTransaction.commit()

  }

  protected fun <T> LiveData<T>.observe(block: (T) -> Unit) {
    observe(this@BaseActivity, Observer {
      if (it == null) return@Observer
      block(it)
    })
  }
}
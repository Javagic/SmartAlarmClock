/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 17:56
 */

package com.javagic.smartalarmclock.utils.ext

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.view.View

fun <V : View> AppCompatActivity.view(@IdRes idRes: Int) = lazy { findViewById<V>(idRes)!! }

inline fun <V : View> AppCompatActivity.view(@IdRes idRes: Int, crossinline initializer: (V) -> Unit) =
    lazy { findViewById<V>(idRes).apply { initializer(this) } }


inline fun <reified T : ViewModel> AppCompatActivity.viewModel(): T =
    ViewModelProviders.of(this)[T::class.java]

/**
 * Возвращает инстанс ViewModel, осведомленной о ЖЦ соответствующей активити.
 * Необходимо использовать, если ViewModel содержит помеченные аннотацией @OnLifecycleEvent методы.
 */
inline fun <reified T> AppCompatActivity.viewModelLifecycleAware(): T
    where T : ViewModel, T : LifecycleObserver = viewModel<T>().apply {
  this@viewModelLifecycleAware.lifecycle.addObserver(this)
}
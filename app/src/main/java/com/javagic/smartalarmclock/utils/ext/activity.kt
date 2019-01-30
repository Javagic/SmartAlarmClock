/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 17:56
 */

package com.javagic.smartalarmclock.utils.ext

import android.Manifest
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.javagic.smartalarmclock.R
import pub.devrel.easypermissions.EasyPermissions

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

val MUSIC_PERMISSIONS = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)

fun AppCompatActivity.withMusicPermission(requestCode: Int, action: () -> Unit) = withPermissions(
    MUSIC_PERMISSIONS,
    R.string.permission_music,
    requestCode,
    action
)

fun AppCompatActivity.withPermissions(permissions: List<String>, @StringRes rationale: Int, requestCode: Int, action: () -> Unit) =
    if (hasPermissions(permissions)) {
      action()
    } else {
      EasyPermissions.requestPermissions(
          this,
          string(rationale),
          requestCode,
          *permissions.toTypedArray()
      )
    }

private fun Context.hasPermissions(permissions: List<String>): Boolean = EasyPermissions.hasPermissions(
    this,
    *permissions.toTypedArray()
)
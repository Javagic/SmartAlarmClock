/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 19:13
 */

package com.javagic.smartalarmclock.ext

import android.support.annotation.IdRes
import android.view.View

@Suppress("UNCHECKED_CAST")
fun <T : View> View.view(@IdRes id: Int) = lazy { findViewById(id) as T }

@Suppress("UNCHECKED_CAST")
inline fun <T : View> View.view(@IdRes id: Int, crossinline initializer: (T) -> T) = lazy { initializer(findViewById<T>(id) as T) }

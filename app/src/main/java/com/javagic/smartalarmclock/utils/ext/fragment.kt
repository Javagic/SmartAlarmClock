/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 18:16
 */

package com.javagic.smartalarmclock.utils.ext

import android.app.Fragment
import android.support.annotation.IdRes
import android.view.View

fun <V : View> Fragment.view(@IdRes idRes: Int) = lazy { activity.findViewById<V>(idRes)!! }

inline fun <V : View> Fragment.view(@IdRes idRes: Int, crossinline initializer: (V) -> Unit) =
    lazy { activity.findViewById<V>(idRes).apply { initializer(this) } }

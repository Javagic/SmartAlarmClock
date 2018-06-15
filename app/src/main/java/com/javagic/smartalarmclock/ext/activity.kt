/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 17:56
 */

package com.javagic.smartalarmclock.ext

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.view.View

fun <V : View> Activity.view(@IdRes idRes: Int) = lazy { findViewById<V>(idRes)!! }

inline fun <V : View> Activity.view(@IdRes idRes: Int, crossinline initializer: (V) -> Unit) =
    lazy { findViewById<V>(idRes).apply { initializer(this) } }

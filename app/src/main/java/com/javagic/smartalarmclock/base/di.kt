package com.javagic.smartalarmclock.base

import android.app.Activity

val appComponent : AppComponent get() = AlarmApp.dagger!!

val Activity.appComponent : AppComponent get() = (application as AlarmApp).dagger!!
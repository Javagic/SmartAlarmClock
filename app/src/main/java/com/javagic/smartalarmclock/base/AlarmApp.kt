package com.javagic.smartalarmclock.base

import android.app.Application

public object AlarmApp : Application() {

    var dagger: AppComponent? = null


    override fun onCreate() {
        super.onCreate()
        dagger = DaggerAppComponent.builder().context(this).build()

    }

}

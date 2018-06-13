package com.javagic.smartalarmclock.base

import android.content.Context
import dagger.BindsInstance
import dagger.Component



@AppScope
@Component()
interface AppComponent {

    val context: Context

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}
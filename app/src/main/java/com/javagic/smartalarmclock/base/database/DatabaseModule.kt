package com.javagic.smartalarmclock.base.database

import android.arch.persistence.room.Room
import android.content.Context
import com.javagic.smartalarmclock.base.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@AppScope
@Module()
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDotaDatabase(context: Context): DotaDatabase {
        return Room.databaseBuilder(context, DotaDatabase::class.java, "dagger.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
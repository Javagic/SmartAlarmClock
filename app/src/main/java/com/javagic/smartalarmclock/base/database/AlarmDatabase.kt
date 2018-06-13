package com.javagic.smartalarmclock.base.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.TypeConverters
import android.content.Context

@Database(entities = [AlarmItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class NytcDatabase : RoomDatabase() {

    abstract fun longJumpRecordDao(): LongJumpRecordDao
}

@Database(version = 1,
        exportSchema = false,
        entities = [(Category::class), (Address::class)]
)
@TypeConverters(TypeConverter::class)
abstract class MapsDB : RoomDatabase() {
    private val jobs: MutableList<Job> = mutableListOf()

    fun getDaoList() = listOf(getCategoryDao(), getAddressDao())

    fun clear(action: (() -> Unit)? = null) {
        val job = launch(UI) {
            async(CommonPool) { getDaoList().forEach { it.delete() } }.await()
            action?.invoke()
        }
        jobs.add(job)
    }

    fun onDestroy() {
        jobs.forEach { it.cancel() }
    }


    abstract fun getCategoryDao(): CategoryDao

    abstract fun getAddressDao(): AddressDao

    companion object {
        private var instance: MapsDB? = null

        fun getInstance(context: Context): MapsDB? {
            if (instance == null) {
                synchronized(MapsDB::class) {
                    instance = Room.databaseBuilder(context, MapsDB::class.java, "fifa2018_friends.mapDB").build()
                }
            }
            return instance
        }

        fun destroy() {
            instance = null
        }
    }

}
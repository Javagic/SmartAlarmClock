package com.javagic.smartalarmclock.base.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "", )
data class AlarmItem(@PrimaryKey id: Int)

@Entity(tableName = "alarms", indices = [(Index(value = ["account_id"], unique = true))])
data class Bookmark(@ColumnInfo(name = "account_id") var id: Long) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "count")
    var count: Int = 0
}
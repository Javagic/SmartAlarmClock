package com.javagic.smartalarmclock.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable

const val ALARM_TABLE = "alarms"
const val ALARM_EXTRA = "AlarmItem"

const val ALARM_ID_EXTRA = "alarm_id"
const val ALARM_NAME_EXTRA = "alarm_name"
const val ALARM_SONG_EXTRA = "alarm_song"
const val ALARM_HOUR_EXTRA = "alarm_hour"
const val ALARM_MINUTE_EXTRA = "alarm_minute"
const val ALARM_SECOND_EXTRA = "alarm_second"
const val ALARM_ENABLED_EXTRA = "alarm_enabled"
const val ALARM_TONE_EXTRA = "alarm_tone"

val EMPTY_ALARM_ITEM = AlarmItem("", "", 1, 1, 1, false, "")

@Entity(tableName = ALARM_TABLE)
data class AlarmItem(var name: String? = null,
                     var song: String,
                     var timeHour: Int,
                     var timeMinute: Int,
                     var second: Int,
                     var enabled: Boolean = true,
                     var musicUri: String
) : Parcelable {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0

  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readInt(),
      parcel.readInt(),
      parcel.readInt(),
      parcel.readByte() != 0.toByte(),
      parcel.readString()) {
    id = parcel.readLong()
  }


  constructor(bundle: Bundle) : this(
      bundle.getString(ALARM_NAME_EXTRA),
      bundle.getString(ALARM_SONG_EXTRA),
      bundle.getInt(ALARM_HOUR_EXTRA),
      bundle.getInt(ALARM_MINUTE_EXTRA),
      bundle.getInt(ALARM_SECOND_EXTRA),
      bundle.getBoolean(ALARM_ENABLED_EXTRA),
      bundle.getString(ALARM_TONE_EXTRA)) {
    bundle.getLong(ALARM_ID_EXTRA)
  }


  fun asBundle() = Bundle().apply {
    putString(ALARM_NAME_EXTRA, name)
    putString(ALARM_SONG_EXTRA, song)
    putInt(ALARM_HOUR_EXTRA, timeHour)
    putInt(ALARM_MINUTE_EXTRA, timeMinute)
    putInt(ALARM_SECOND_EXTRA, second)
    putBoolean(ALARM_ENABLED_EXTRA, enabled)
    putString(ALARM_TONE_EXTRA, musicUri)
    putLong(ALARM_ID_EXTRA, id)
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(name)
    parcel.writeString(song)
    parcel.writeInt(timeHour)
    parcel.writeInt(timeMinute)
    parcel.writeInt(second)
    parcel.writeByte(if (enabled) 1 else 0)
    parcel.writeString(musicUri)
    parcel.writeLong(id)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<AlarmItem> {
    override fun createFromParcel(parcel: Parcel): AlarmItem {
      return AlarmItem(parcel)
    }

    override fun newArray(size: Int): Array<AlarmItem?> {
      return arrayOfNulls(size)
    }
  }
}
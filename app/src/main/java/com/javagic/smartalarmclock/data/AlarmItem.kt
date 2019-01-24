package com.javagic.smartalarmclock.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

const val ALARM_TABLE = "alarms"
const val ALARM_EXTRA = "AlarmItem"

val EMPTY_ALARM_ITEM = AlarmItem("", "", 1, 1, 1, false, false)

@Entity(tableName = ALARM_TABLE)
data class AlarmItem(val name: String? = null,
                     val song: String,
                     val timeHour: Int,
                     val timeMinute: Int,
                     val second: Int,
                     val enabled: Boolean,
                     val tone: Boolean
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
      parcel.readByte() != 0.toByte()) {
    id = parcel.readLong()
  }

  fun toMinimal() = AlarmItemMinimal(name, timeHour, timeMinute)
  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(name)
    parcel.writeString(song)
    parcel.writeInt(timeHour)
    parcel.writeInt(timeMinute)
    parcel.writeInt(second)
    parcel.writeByte(if (enabled) 1 else 0)
    parcel.writeByte(if (tone) 1 else 0)
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
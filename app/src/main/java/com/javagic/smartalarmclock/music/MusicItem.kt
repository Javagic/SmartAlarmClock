package com.javagic.smartalarmclock.music

import android.os.Parcel
import android.os.Parcelable

const val EXTRA_MUSIC_ITEM = "MusicItem"

class MusicItem(val name: String, val duration: String, val uri: String) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readString())

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(name)
    parcel.writeString(duration)
    parcel.writeString(uri)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<MusicItem> {
    override fun createFromParcel(parcel: Parcel): MusicItem {
      return MusicItem(parcel)
    }

    override fun newArray(size: Int): Array<MusicItem?> {
      return arrayOfNulls(size)
    }
  }

}
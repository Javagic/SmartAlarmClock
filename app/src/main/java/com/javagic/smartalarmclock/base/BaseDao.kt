/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.01.19 16:58
 */

package com.javagic.smartalarmclock.base

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Update


interface BaseDao<T> {

  @Insert
  fun insert(obj: T): Long

  @Update
  fun update(obj: T)

  @Update
  fun update(objects: List<T>)

  @Delete
  fun delete(obj: T)
}
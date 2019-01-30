package com.javagic.smartalarmclock.trigger

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import io.reactivex.subjects.BehaviorSubject

class SensorManager : SensorEventListener {
  val events: BehaviorSubject<SensorEvent> = BehaviorSubject.create<SensorEvent>()
  override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
  }

  override fun onSensorChanged(event: SensorEvent?) {
    event?.let {
      events.onNext(it)
    }
  }

}
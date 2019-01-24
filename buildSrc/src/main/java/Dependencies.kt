import Versions.androidSupport
import Versions.archVersion
import Versions.canaryVersion
import Versions.constraintLayoutVersion
import Versions.daggerVersion
import Versions.gradle
import Versions.jUnitVersion
import Versions.jacksonVersion
import Versions.kotlin
import Versions.multidexVersion
import Versions.overscrollVersion
import Versions.roomDatabase
import Versions.rxandroid2Version
import Versions.rxbinding2Version
import Versions.rxjava2Version
import Versions.rxkotlin2Version
import Versions.swipePickerVersion
import Versions.testRunnerVersion
import Versions.timberVersion

/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 23.01.19 19:47
 */

object Versions {
  const val kotlin = "1.3.11"
  const val gradle = "3.3.0"
  const val androidSupport = "27.1.1"
  const val roomDatabase = "1.1.0-alpha3"
  const val coroutines = "1.1.0"
  const val multidexVersion = "1.0.3"
  const val constraintLayoutVersion = "1.1.1"
  const val jUnitVersion = "1.1.1"
  const val testRunnerVersion = "1.0.2"
  const val daggerVersion = "2.15"
  const val canaryVersion = "1.5.4"
  const val archVersion = "1.1.1"
  const val timberVersion = "4.7.1"
  const val overscrollVersion = "1.0.4"
  const val swipePickerVersion = "1.1.0"
  const val rxandroid2Version = "2.0.1"
  const val rxkotlin2Version = "2.1.0"
  const val rxjava2Version = "2.1.6"
  const val rxbinding2Version = "2.1.0"
  const val jacksonVersion = "2.9.5"

  const val buildToolsVersion = "27.0.3"
  const val compileSdkVersion = 27
  const val minSdkVersion = 22
  const val playServiceVersion = "11.8.0"
  const val targetSdkVersion = 22
}

object Deps {
  val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin"
  val androidGradlePlugin = "com.android.tools.build:gradle:$gradle"
  val androidV7 = "com.android.support:appcompat-v7:${Versions.androidSupport}"
  val room = "android.arch.persistence.room:runtime:$roomDatabase"
  val roomCompiler = "android.arch.persistence.room:compiler:$roomDatabase"
  val roomTesting = "android.arch.persistence.room:testing:$roomDatabase"
  val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
  val androidCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
  val design = "com.android.support:design:$androidSupport"
  val supportV13 = "com.android.support:support-v13:$androidSupport"
  val recyclerView = "com.android.support:recyclerview-v7:$androidSupport"
  val cardView = "com.android.support:cardview-v7:$androidSupport"
  val multidex = "com.android.support:multidex:$multidexVersion"
  val constraintLayout = "com.android.support.constraint:constraint-layout:$constraintLayoutVersion"
  val jUnit = "junit:junit:$jUnitVersion"
  val testRunner = "com.android.support.test:runner:$testRunnerVersion"
  val dagger = "com.google.dagger:dagger:$daggerVersion"
  val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
  val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
  val daggerAndroid = "com.google.dagger:dagger-android:$daggerVersion"
  val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"
  val rxjava = "io.reactivex.rxjava2:rxjava:$rxjava2Version"
  val rxAndroid = "io.reactivex.rxjava2:rxandroid:$rxandroid2Version"
  val rxKotlin = "io.reactivex.rxjava2:rxkotlin:$rxkotlin2Version"
  val rxbinding = "com.jakewharton.rxbinding2:rxbinding:$rxbinding2Version"
  val rxbindingKotlin = "com.jakewharton.rxbinding2:rxbinding-kotlin:$rxbinding2Version"
  val rxbindingSupport = "com.jakewharton.rxbinding2:rxbinding-support-v4-kotlin:$rxbinding2Version"
  val jacksonCore = "com.fasterxml.jackson.core:jackson-core:$jacksonVersion"
  val jacksonAnnotations = "com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion"
  val jacksonDatabind = "com.fasterxml.jackson.core:jackson-databind:$jacksonVersion"
  val jacksonKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion"
  val leakCanary = "com.squareup.leakcanary:leakcanary-android:$canaryVersion"
  val leakCanaryOp = "com.squareup.leakcanary:leakcanary-android-no-op:$canaryVersion"
  val timber = "com.jakewharton.timber:timber:$timberVersion"
  val lifecycleExtensions = "android.arch.lifecycle:extensions:$archVersion"
  val lifecycleRuntime = "android.arch.lifecycle:runtime:$archVersion"
  val lifecycleCompiler = "android.arch.lifecycle:compiler:$archVersion"
  val overscrollDecorator = "me.everything:overscroll-decor-android:$overscrollVersion"
  val swipePicker = "one.xcorp.widget:swipe-picker:$swipePickerVersion"

}

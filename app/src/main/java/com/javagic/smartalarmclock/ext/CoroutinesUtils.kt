/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 23.01.19 19:06
 */

package com.javagic.smartalarmclock.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job


//fun launchDb(block: suspend CoroutineScope.() -> Unit): Job = launch(CommonPool, block = block)
//
//fun <T> asyncDb(block: suspend CoroutineScope.() -> T): Deferred<T> = async(CommonPool, block = block)
//
//fun ui(block: suspend CoroutineScope.() -> Unit): Job = launch(UI, block = block)
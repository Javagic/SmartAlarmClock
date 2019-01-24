/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 18:09
 */

package com.javagic.smartalarmclock.utils.ext

import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

fun Disposable.bind(container: DisposableContainer): Disposable = also { container.add(it) }
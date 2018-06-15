/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 16.10.18 18:09
 */

package com.javagic.smartalarmclock.base


interface BasePresenter<T> {

  fun takeView(view: T)

  fun dropView()
}

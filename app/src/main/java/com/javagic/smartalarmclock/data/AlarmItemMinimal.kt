/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.06.18 19:20
 */

package com.javagic.smartalarmclock.data


data class AlarmItemMinimal(val id : Long, val name: String? = null, val timeHour: Int, val timeMinute: Int)
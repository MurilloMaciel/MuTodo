package com.maciel.murillo.mutodo.core.extensions

import java.text.SimpleDateFormat
import java.util.*

private const val ONE_HOUR_IN_MILLIS = 3600000L
private const val HALF_DAY_IN_MILLIS = 43200000L
private const val ONE_DAY_IN_MILLIS = 86400000L
private const val TWO_DAYS_IN_MILLIS = 172800000L
private const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"

fun String.toDateTimeCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    val sdf = SimpleDateFormat(DATE_TIME_FORMAT)
    calendar.time = sdf.parse(this)!!

    return calendar
}

fun Calendar.toDateTimeString(): String {
    return SimpleDateFormat(DATE_TIME_FORMAT).format(time)
}

fun Calendar.isCurrentYear(): Boolean {
    val today = Calendar.getInstance()
    return today.get(Calendar.YEAR) == get(Calendar.YEAR)
}

fun Calendar.isYesterday(): Boolean {
    return after(getFinalCalendarForBeforeYesterday()) && before(getInitialCalendarForToday())
}

fun Calendar.isToday(): Boolean {
    return after(getFinalCalendarForYesterday()) && before(getInitialCalendarForTomorrow())
}

fun Calendar.isNotToday() = isToday().not()

fun Calendar.isTomorrow(): Boolean {
    return after(getFinalCalendarForToday()) && before(getInitialCalendarForAfterTomorrow())
}

fun Calendar.isAfterTomorrow() = after(getFinalCalendarForTomorrow())

fun Calendar.isAfterToday() = after(getFinalCalendarForToday())

fun Calendar.isAfterYesterday() = after(getFinalCalendarForYesterday())

fun Calendar.isBeforeToday() = before(getInitialCalendarForToday())

fun Calendar.isBeforeYesterday() = before(getInitialCalendarForYesterday())

fun Calendar.isBeforeNow() = before(Calendar.getInstance())

fun Calendar.isAfterNow() = after(Calendar.getInstance())

fun Calendar.hasLessThenOneHourFromNow() = hasLessThenSometimeFromNow(ONE_HOUR_IN_MILLIS)

fun Calendar.hasLessThenHalfDayFromNow() = hasLessThenSometimeFromNow(HALF_DAY_IN_MILLIS)

fun Calendar.hasLessThenOneDayFromNow() = hasLessThenSometimeFromNow(ONE_DAY_IN_MILLIS)

fun Calendar.hasLessThenSometimeFromNow(timeInMillis: Long): Boolean {
    val nowInMillis = Calendar.getInstance().timeInMillis
    return ( this.timeInMillis - nowInMillis ) < timeInMillis
}

fun getInitialAlarm(): Calendar {
    val currentTime = Calendar.getInstance()

    currentTime.set(Calendar.SECOND, 0)
    currentTime.set(Calendar.MILLISECOND, 0)

    var hour = currentTime.get(Calendar.HOUR_OF_DAY)
    val minute = currentTime.get(Calendar.MINUTE)

    if (minute in 0..29) {
        currentTime.set(Calendar.MINUTE, 30)

    } else if (minute in 30..59) {

        currentTime.set(Calendar.MINUTE, 0)

        if (hour == 23) {
            currentTime.set(Calendar.HOUR_OF_DAY, 0)
            currentTime.add(Calendar.DAY_OF_MONTH, 1)
        } else {
            hour++
            currentTime.set(Calendar.HOUR_OF_DAY, hour)
        }

    }

    return currentTime
}

fun Calendar.getDayName(): String {
    val day = SimpleDateFormat("EEEE").format(time)
    return day.capitalize()
}

fun getInitialCalendarForToday() = Calendar.getInstance().clearTime()

fun getFinalCalendarForToday() = Calendar.getInstance().setFinalTimeToCalendar()

fun getFinalCalendarForYesterday(): Calendar {
    val yesterday = getFinalCalendarForToday()
    yesterday.add(Calendar.DAY_OF_MONTH, -1)

    return yesterday
}

fun getFinalCalendarForBeforeYesterday(): Calendar {
    val yesterday = getFinalCalendarForYesterday()
    yesterday.add(Calendar.DAY_OF_MONTH, -1)

    return yesterday
}

fun getFinalCalendarForTomorrow(): Calendar {
    val tomorrow = getFinalCalendarForToday()
    tomorrow.add(Calendar.DAY_OF_MONTH, 1)

    return tomorrow
}

fun getInitialCalendarForTomorrow(): Calendar {
    val tomorrow = getInitialCalendarForToday()
    tomorrow.add(Calendar.DAY_OF_MONTH, 1)

    return tomorrow
}

fun getInitialCalendarForAfterTomorrow(): Calendar {
    val afterTomorrow = getInitialCalendarForToday()
    afterTomorrow.add(Calendar.DAY_OF_MONTH, 2)

    return afterTomorrow
}

fun getInitialCalendarForYesterday(): Calendar {
    val yesterday = getInitialCalendarForToday()
    yesterday.add(Calendar.DAY_OF_MONTH, -1)

    return yesterday
}

fun Calendar.clearTime(): Calendar {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)

    return this
}

fun Calendar.setFinalTimeToCalendar(): Calendar {
    set(Calendar.HOUR_OF_DAY, 23)
    set(Calendar.MINUTE, 59)
    set(Calendar.SECOND, 59)
    set(Calendar.MILLISECOND, 0)

    return this
}

fun Calendar.getDateToString(): String {
    val year = this.get(Calendar.YEAR).toString().fiilWithDefaultText(4, "0")
    val month = (this.get(Calendar.MONTH)+1).toString().fiilWithDefaultText(2, "0")
    val day = this.get(Calendar.DAY_OF_MONTH).toString().fiilWithDefaultText(2, "0")
    return "$year/$month/$day"
}

fun Calendar.getTimeToString(): String {
    val hour = this.get(Calendar.HOUR_OF_DAY).toString().fiilWithDefaultText(2, "0")
    val minute = this.get(Calendar.MINUTE).toString().fiilWithDefaultText(2, "0")
    return "$hour:$minute"
}
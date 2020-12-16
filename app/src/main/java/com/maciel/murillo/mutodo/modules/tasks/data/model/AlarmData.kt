package com.maciel.murillo.mutodo.modules.tasks.data.model

import com.maciel.murillo.mutodo.core.extensions.toDateTimeCalendar
import com.maciel.murillo.mutodo.core.extensions.toDateTimeString
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.model.RepeatType

data class AlarmData(
//        @ColumnInfo(name = "repeatType") var repeatType: Int = 0,
//        @ColumnInfo(name = "nextAlarmDate") var nextAlarmDate: String?,
//        @ColumnInfo(name = "customDays") var customDays: String?
        val repeatType: Int = 0,
        val nextAlarmDate: String?,
        val customDays: String?
)

fun AlarmData.mapToAlarm() = Alarm(
        dateTime = nextAlarmDate!!.toDateTimeCalendar(),
        repeatType = RepeatType.indexOf(repeatType),
        customDays = customDays
)

fun Alarm.mapToAlarmData() = AlarmData(
        repeatType = repeatType.ordinal,
        nextAlarmDate = dateTime.toDateTimeString(),
        customDays = customDays
)
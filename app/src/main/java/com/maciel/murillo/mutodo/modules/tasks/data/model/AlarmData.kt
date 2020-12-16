package com.maciel.murillo.mutodo.modules.tasks.data.model

import com.maciel.murillo.mutodo.core.extensions.toDateTimeCalendar
import com.maciel.murillo.mutodo.core.extensions.toDateTimeString
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.model.RepeatType

data class AlarmData(
        var repeatType: Int = 0,
        var nextAlarmDate: String?,
        var customDays: String?
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
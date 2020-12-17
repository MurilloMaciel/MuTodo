package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm

import com.maciel.murillo.mutodo.core.extensions.isBeforeNow
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.model.RepeatType
import java.util.*

class GetNextAlarmUseCase {

    operator fun invoke(alarm: Alarm): Alarm {
        do { incrementToNext(alarm) } while (alarm.dateTime.isBeforeNow())
        return alarm
    }

    private fun incrementToNext(alarm: Alarm) {
        when (alarm.repeatType) {
            RepeatType.SECOND -> alarm.dateTime.add(Calendar.SECOND, 1)
            RepeatType.MINUTE -> alarm.dateTime.add(Calendar.MINUTE, 1)
            RepeatType.HOUR -> alarm.dateTime.add(Calendar.HOUR_OF_DAY, 1)
            RepeatType.DAY -> alarm.dateTime.add(Calendar.DAY_OF_MONTH, 1)
            RepeatType.WEEK -> alarm.dateTime.add(Calendar.WEEK_OF_MONTH, 1)
            RepeatType.MONTH -> alarm.dateTime.add(Calendar.MONTH, 1)
            RepeatType.YEAR -> alarm.dateTime.add(Calendar.YEAR, 1)
            RepeatType.CUSTOM -> alarm.customDays?.run { incrementToNextCustomAlarm(alarm.dateTime, this) }
            RepeatType.NOT_REPEAT -> return
        }
    }

    private fun incrementToNextCustomAlarm(calendar: Calendar, days: String) {
        do {
            calendar.add(Calendar.DATE, 1)
            val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK).toString()
        } while (days.contains(currentDayOfWeek).not())
    }
}
package com.maciel.murillo.mutodo.modules.tasks.data.local

import com.maciel.murillo.mutodo.core.extensions.toDateTimeCalendar
import com.maciel.murillo.mutodo.core.platform.AlarmManager
import com.maciel.murillo.mutodo.modules.tasks.data.datasource.AlarmLocalDatasource
import com.maciel.murillo.mutodo.modules.tasks.data.model.AlarmData
import java.util.*

class AlarmLocalDatasourceImpl(private val alarmManager: AlarmManager) : AlarmLocalDatasource {

    override suspend fun scheduleAlarm(alarm: AlarmData, taskId: Long) {
        alarm.nextAlarmDate?.run { alarmManager.scheduleAlarm(this.toDateTimeCalendar(), taskId) }
    }

    override suspend fun updateAlarm(calendar: Calendar) = alarmManager.scheduleAlarmUpdates(calendar)

    override suspend fun cancelAlarm(taskId: Long) = alarmManager.cancelAlarm(taskId)
}
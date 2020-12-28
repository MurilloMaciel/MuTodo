package com.maciel.murillo.mutodo.modules.tasks.data.local

import com.maciel.murillo.mutodo.core.platform.AlarmManager
import com.maciel.murillo.mutodo.modules.tasks.data.datasource.AlarmLocalDatasource
import com.maciel.murillo.mutodo.modules.tasks.data.model.TaskData
import java.util.*

class AlarmLocalDatasourceImpl(private val alarmManager: AlarmManager) : AlarmLocalDatasource {

    override suspend fun scheduleAlarm(task: TaskData) = alarmManager.scheduleAlarm(task)

    override suspend fun cancelAlarm(task: TaskData) = alarmManager.cancelAlarm(task)

    override suspend fun scheduleAlarmUpdates(calendar: Calendar) = alarmManager.scheduleAlarmUpdates(calendar)

    override suspend fun updateAlarmSchedule(task: TaskData) = alarmManager.updateAlarm(task)
}
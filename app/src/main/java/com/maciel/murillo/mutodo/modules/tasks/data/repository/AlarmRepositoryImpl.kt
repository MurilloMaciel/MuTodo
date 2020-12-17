package com.maciel.murillo.mutodo.modules.tasks.data.repository

import com.maciel.murillo.mutodo.modules.tasks.data.datasource.AlarmLocalDatasource
import com.maciel.murillo.mutodo.modules.tasks.data.model.mapToAlarmData
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.AlarmRepository
import java.util.*

class AlarmRepositoryImpl(private val localDatasource: AlarmLocalDatasource) : AlarmRepository {

    override suspend fun scheduleAlarm(alarm: Alarm, taskId: Long) = localDatasource.scheduleAlarm(alarm.mapToAlarmData(), taskId)

    override suspend fun updateAlarm(calendar: Calendar) = localDatasource.updateAlarm(calendar)

    override suspend fun cancelAlarm(taskId: Long) = localDatasource.cancelAlarm(taskId)
}
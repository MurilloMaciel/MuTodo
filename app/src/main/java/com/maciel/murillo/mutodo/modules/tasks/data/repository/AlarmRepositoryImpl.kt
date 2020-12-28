package com.maciel.murillo.mutodo.modules.tasks.data.repository

import com.maciel.murillo.mutodo.modules.tasks.data.datasource.AlarmLocalDatasource
import com.maciel.murillo.mutodo.modules.tasks.data.model.mapToTaskData
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.AlarmRepository
import java.util.*

class AlarmRepositoryImpl(private val localDatasource: AlarmLocalDatasource) : AlarmRepository {

    override suspend fun scheduleAlarm(task: Task) = localDatasource.scheduleAlarm(task.mapToTaskData())

    override suspend fun scheduleAlarmUpdates(calendar: Calendar) = localDatasource.scheduleAlarmUpdates(calendar)

    override suspend fun cancelAlarm(task: Task) = localDatasource.cancelAlarm(task.mapToTaskData())

    override suspend fun updateAlarmSchedule(task: Task) = localDatasource.scheduleAlarm(task.mapToTaskData())
}
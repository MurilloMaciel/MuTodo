package com.maciel.murillo.mutodo.modules.tasks.data.datasource

import com.maciel.murillo.mutodo.modules.tasks.data.model.TaskData
import java.util.*

interface AlarmLocalDatasource {

    suspend fun scheduleAlarm(task: TaskData)

    suspend fun scheduleAlarmUpdates(calendar: Calendar)

    suspend fun cancelAlarm(task: TaskData)

    suspend fun updateAlarmSchedule(task: TaskData)
}
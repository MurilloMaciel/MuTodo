package com.maciel.murillo.mutodo.modules.tasks.domain.repository

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import java.util.*

interface AlarmRepository {

    suspend fun scheduleAlarm(task: Task)

    suspend fun scheduleAlarmUpdates(calendar: Calendar)

    suspend fun cancelAlarm(task: Task)

    suspend fun updateAlarmSchedule(task: Task)
}
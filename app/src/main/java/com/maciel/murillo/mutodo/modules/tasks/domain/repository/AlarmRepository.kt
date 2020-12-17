package com.maciel.murillo.mutodo.modules.tasks.domain.repository

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import java.util.*

interface AlarmRepository {

    suspend fun scheduleAlarm(alarm: Alarm, taskId: Long)

    suspend fun updateAlarm(calendar: Calendar)

    suspend fun cancelAlarm(taskId: Long)
}
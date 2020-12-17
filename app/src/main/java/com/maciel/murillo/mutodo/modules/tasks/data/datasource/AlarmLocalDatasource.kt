package com.maciel.murillo.mutodo.modules.tasks.data.datasource

import com.maciel.murillo.mutodo.modules.tasks.data.model.AlarmData
import java.util.*

interface AlarmLocalDatasource {

    suspend fun scheduleAlarm(alarm: AlarmData, taskId: Long)

    suspend fun updateAlarm(calendar: Calendar)

    suspend fun cancelAlarm(taskId: Long)
}
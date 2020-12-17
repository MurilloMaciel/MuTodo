package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.AlarmRepository

class ScheduleAlarmUseCase(private val alarmRepository: AlarmRepository) {

    suspend operator fun invoke(alarm: Alarm, taskId: Long) = alarmRepository.scheduleAlarm(alarm, taskId)
}
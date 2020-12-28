package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.AlarmRepository

class ScheduleAlarmUseCase(private val alarmRepository: AlarmRepository) {

    suspend operator fun invoke(task: Task) = alarmRepository.scheduleAlarm(task)
}
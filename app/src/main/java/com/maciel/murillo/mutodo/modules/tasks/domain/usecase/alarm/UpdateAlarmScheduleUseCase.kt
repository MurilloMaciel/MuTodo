package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.AlarmRepository

class UpdateAlarmScheduleUseCase(private val alarmRepository: AlarmRepository) {

    suspend operator fun invoke(task: Task) = alarmRepository.updateAlarmSchedule(task)
}
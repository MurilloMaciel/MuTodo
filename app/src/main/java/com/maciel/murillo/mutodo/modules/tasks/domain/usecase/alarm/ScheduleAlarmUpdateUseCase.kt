package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm

import com.maciel.murillo.mutodo.core.extensions.getInitialCalendarForTomorrow
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.AlarmRepository

class ScheduleAlarmUpdateUseCase(private val alarmRepository: AlarmRepository) {

    suspend operator fun invoke() {
        val calendar = getInitialCalendarForTomorrow()
        alarmRepository.updateAlarm(calendar)
    }
}
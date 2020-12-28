package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm

import com.maciel.murillo.mutodo.core.extensions.isAfterNow
import com.maciel.murillo.mutodo.core.extensions.isBeforeNow
import com.maciel.murillo.mutodo.core.extensions.safe
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.GetAllTasksUseCase

class ScheduleAllAlarmsUseCase(private val getAllTasksUseCase: GetAllTasksUseCase,
                               private val getNextAlarmUseCase: GetNextAlarmUseCase,
                               private val scheduleAlarmUseCase: ScheduleAlarmUseCase) {

    suspend operator fun invoke() {
        val tasks = getAllTasksUseCase.invoke()

        tasks.forEach { task ->
            task.alarm?.let {
                var alarm = it

                if (alarm.dateTime.isBeforeNow() && alarm.isAlarmRepeating()) {
                    alarm = getNextAlarmUseCase.invoke(alarm)
                }

                if (alarm.dateTime.isAfterNow()) {
                    scheduleAlarmUseCase.invoke(task)
                }
            }
        }
    }
}
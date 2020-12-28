package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm

import com.maciel.murillo.mutodo.core.extensions.isAfterNow
import com.maciel.murillo.mutodo.core.extensions.isBeforeNow
import com.maciel.murillo.mutodo.core.extensions.safe
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.GetAllTasksUseCase

class ScheduleAllAlarmsUseCase(private val getAllTasksUseCase: GetAllTasksUseCase,
                               private val scheduleAlarmUseCase: ScheduleAlarmUseCase) {

    suspend operator fun invoke() {
        val tasks = getAllTasksUseCase.invoke()

        tasks.forEach { task ->
            task.alarm?.run {
                if (this.dateTime.isBeforeNow() && this.isAlarmRepeating()) {
                    scheduleAlarmUseCase.invoke(task)
                } else if (this.dateTime.isAfterNow()) {
                    scheduleAlarmUseCase.invoke(task)
                }
            }
        }
    }
}
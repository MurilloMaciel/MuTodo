package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.core.extensions.isNull
import com.maciel.murillo.mutodo.core.extensions.safe
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.CancelAlarmScheduleUseCase
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.ScheduleAlarmUpdateUseCase
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.ScheduleAlarmUseCase

class UpdateTaskUseCase(private val repository: TaskRepository,
                        private val scheduleAlarmUpdateUseCase: ScheduleAlarmUpdateUseCase,
                        private val cancelAlarmScheduleUseCase: CancelAlarmScheduleUseCase) {

    suspend operator fun invoke(task: Task) {
        repository.update(task)

        if (task.alarm.isNull()) {
            cancelAlarmScheduleUseCase.invoke(task.id.safe())
        } else {
            scheduleAlarmUpdateUseCase.invoke()
        }
    }
}
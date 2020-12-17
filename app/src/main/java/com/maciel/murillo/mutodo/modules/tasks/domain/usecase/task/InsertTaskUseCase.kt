package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.ScheduleAlarmUseCase

class InsertTaskUseCase(private val repository: TaskRepository,
                        private val scheduleAlarmUseCase: ScheduleAlarmUseCase) {

    suspend operator fun invoke(task: Task) {
        repository.insert(task)
        task.alarm?.run { scheduleAlarmUseCase.invoke(this, task.id) }
    }
}
package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.ScheduleAlarmUseCase

class InsertTaskListUseCase(private val repository: TaskRepository,
                            private val scheduleAlarmUseCase: ScheduleAlarmUseCase) {

    suspend operator fun invoke(tasks: List<Task>) {
        repository.insertAll(tasks)
        tasks.forEach { it.alarm?.run { scheduleAlarmUseCase.invoke(it) } }
    }
}
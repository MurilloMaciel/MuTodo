package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.core.extensions.safe
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.CancelAlarmScheduleUseCase

class DeleteTaskUseCase(private val repository: TaskRepository,
                        private val cancelAlarmScheduleUseCase: CancelAlarmScheduleUseCase) {

    suspend operator fun invoke(task: Task) {
        repository.delete(task.id.safe())
        task.alarm?.run {  cancelAlarmScheduleUseCase.invoke(task.id.safe()) }
    }
}
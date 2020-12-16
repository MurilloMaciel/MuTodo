package com.maciel.murillo.mutodo.modules.tasks.domain.usecase

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository

class deleteTaskUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(task: Task) {
        repository.delete(task.id)

        task.alarm?.run {
            // TODO: 15/12/2020 remove schedule alarm into alarm manager
        }
    }
}
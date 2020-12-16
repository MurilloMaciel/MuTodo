package com.maciel.murillo.mutodo.modules.tasks.domain.usecase

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository

class InsertTaskUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(task: Task) {
        repository.insert(task)

        task.alarm?.run {
            // TODO: 15/12/2020 schedule alarm into alarm manager
        }
    }
}
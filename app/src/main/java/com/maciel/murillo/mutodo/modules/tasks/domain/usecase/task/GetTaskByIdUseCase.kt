package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository

class GetTaskByIdUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(id: Long): Task {
        return repository.findById(id)
    }
}